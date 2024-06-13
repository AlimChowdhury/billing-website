package com.example.billingWebsite;

import com.example.billingWebsite.model.CustomerAccount;
import com.example.billingWebsite.model.CustomerOrder;
import com.example.billingWebsite.model.Item;
import com.example.billingWebsite.model.Transaction;
import com.example.billingWebsite.repository.ItemRepository;
import com.example.billingWebsite.repository.TransactionRepository;
import com.example.billingWebsite.service.CustomerAccountService;
import com.example.billingWebsite.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final CustomerAccountService customerAccountService;

    private final CustomerOrderService customerOrderService;

    private final TransactionRepository transactionRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ScheduledTasks(CustomerAccountService customerAccountService, CustomerOrderService customerOrderService, TransactionRepository transactionRepository, ItemRepository itemRepository) {
        this.customerAccountService = customerAccountService;
        this.customerOrderService = customerOrderService;
        this.transactionRepository = transactionRepository;
        this.itemRepository = itemRepository;
    }

    private final Random random = new Random();

    private final ConcurrentMap<String, Object> taskResults = new ConcurrentHashMap<>();

    @Scheduled(cron = "0 */5 * * * *")
    public void createCustomerAccountTask() {
        CustomerAccount account = new CustomerAccount();
        // Set properties for the account as needed
        CustomerAccount createdAccount = customerAccountService.createCustomerAccount(account);
        taskResults.put("createCustomerAccountTask", createdAccount);
        logger.info("createCustomerAccountTask result: {}", createdAccount);
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void createOrderTask() {
        Long customerId = 1L; // Dummy customerId
        CustomerOrder orderRequest = new CustomerOrder();

        // Randomly select a valid item ID between 1 and 30
        int randomItemId = 1 + random.nextInt(30);
        Optional<Item> optionalItem = itemRepository.findById((long) randomItemId);

        if (optionalItem.isPresent()) {
            orderRequest.setItem(optionalItem.get());
        } else {
            String error = "Valid item not found for creating order with ID: " + randomItemId;
            logger.error(error);
            taskResults.put("createOrderTask", error);
            return;
        }

        try {
            CustomerOrder createdOrder = customerOrderService.createOrder(customerId, orderRequest);
            taskResults.put("createOrderTask", createdOrder);
            logger.info("createOrderTask result: {}", createdOrder);
        } catch (RuntimeException e) {
            logger.error("Error creating order: {}", e.getMessage());
            taskResults.put("createOrderTask", e.getMessage());
        }
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void depositTask() {
        Long customerId = 1L; // Dummy customerId
        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);
        try {
            customerAccountService.deposit(customerId, transaction);
            String successMessage = "Deposit successful for customerId: " + customerId;
            taskResults.put("depositTask", successMessage);
            logger.info(successMessage);
        } catch (RuntimeException e) {
            logger.error("Error processing deposit: {}", e.getMessage());
            taskResults.put("depositTask", e.getMessage());
        }
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void processExpenseTask() {
        Long customerId = 1L; // Dummy customerId
        Transaction transaction = new Transaction();
        transaction.setAmount(50.0);

        CustomerOrder dummyOrder = new CustomerOrder();
        dummyOrder.setId((long) (1 + random.nextInt(30))); // Randomly set a valid order ID between 1 and 30
        transaction.setCustomerOrder(dummyOrder);

        try {
            customerAccountService.processExpense(customerId, transaction);
            String successMessage = "Expense processed successfully for customerId: " + customerId;
            taskResults.put("processExpenseTask", successMessage);
            logger.info(successMessage);
        } catch (RuntimeException e) {
            logger.error("Error processing expense: {}", e.getMessage());
            taskResults.put("processExpenseTask", e.getMessage());
        }
    }

    public Object getTaskResult(String taskName) {
        return taskResults.get(taskName);
    }
}

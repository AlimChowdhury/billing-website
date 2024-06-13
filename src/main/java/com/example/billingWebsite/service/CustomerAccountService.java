package com.example.billingWebsite.service;

import com.example.billingWebsite.model.CustomerAccount;
import com.example.billingWebsite.model.CustomerOrder;
import com.example.billingWebsite.model.Transaction;
import com.example.billingWebsite.repository.CustomerAccountRepository;
import com.example.billingWebsite.repository.CustomerOrderRepository;
import com.example.billingWebsite.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerAccountService {
    private final CustomerAccountRepository customerAccountRepository;
    private final TransactionRepository transactionRepository;

    private final CustomerOrderRepository customerOrderRepository;


    @Autowired
    public CustomerAccountService(CustomerAccountRepository repository, TransactionRepository transactionRepository, CustomerOrderRepository customerOrderRepository) {
        this.customerAccountRepository = repository;
        this.transactionRepository = transactionRepository;
        this.customerOrderRepository = customerOrderRepository;
    }

    @Transactional
    public CustomerAccount createCustomerAccount( CustomerAccount account){
        return customerAccountRepository.save(account);
    }

    @Transactional
    public Optional<CustomerAccount> findCustomerAccount(Long id) {
        return customerAccountRepository.findById(id);
    }



    @Transactional
    public void deposit(Long customerId, Transaction transaction) {
        CustomerAccount customer = customerAccountRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        double amount = transaction.getAmount();
        customer.setAllowedCreditAmount(customer.getAllowedCreditAmount() + amount);
        customerAccountRepository.save(customer);

        transaction.setCustomerAccount(customer);
        transaction.setTransactionType("DEPOSIT");

        transactionRepository.save(transaction);
    }

    @Transactional
    public void processExpense(Long customerId, Transaction transaction) {
        CustomerAccount customer = customerAccountRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        double amount = transaction.getAmount();
        Long orderId = transaction.getCustomerOrder().getId();

        if (customer.getAllowedCreditAmount() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        customer.setAllowedCreditAmount(customer.getAllowedCreditAmount() - amount);
        customerAccountRepository.save(customer);

        transaction.setCustomerAccount(customer);
        transaction.setTransactionType("EXPENSE");

        CustomerOrder order = customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        transaction.setCustomerOrder(order);

        order.setTransactionAllowed(true);
        customerOrderRepository.save(order);

        transactionRepository.save(transaction);
    }
}

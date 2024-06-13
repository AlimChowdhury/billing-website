package com.example.billingWebsite.service;

import com.example.billingWebsite.model.CustomerAccount;
import com.example.billingWebsite.model.CustomerOrder;
import com.example.billingWebsite.model.Item;
import com.example.billingWebsite.model.ItemPriceList;
import com.example.billingWebsite.repository.CustomerAccountRepository;
import com.example.billingWebsite.repository.CustomerOrderRepository;
import com.example.billingWebsite.repository.ItemPriceListRepository;
import com.example.billingWebsite.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;

    private final CustomerAccountRepository customerAccountRepository;

    private final ItemRepository itemRepository;
    private final ItemPriceListRepository itemPriceListRepository;

    @Autowired
    public CustomerOrderService(CustomerOrderRepository customerOrderRepository, CustomerAccountRepository customerAccountRepository, ItemRepository itemRepository, ItemPriceListRepository itemPriceListRepository) {
        this.customerOrderRepository = customerOrderRepository;
        this.customerAccountRepository = customerAccountRepository;
        this.itemRepository = itemRepository;
        this.itemPriceListRepository = itemPriceListRepository;
    }

    @Transactional
    public Optional<CustomerOrder> findCustomerOrderAccount(Long id) {
        return customerOrderRepository.findById(id);
    }


    @Transactional
    public CustomerOrder createOrder(Long customerId, CustomerOrder orderRequest) {
        CustomerAccount customer = customerAccountRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("CustomerAccount not found with id: " + customerId));

        if (orderRequest.getItem() == null) {
            throw new RuntimeException("Order request must include an item");
        }

        Item item = itemRepository.findById(orderRequest.getItem().getId())
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + orderRequest.getItem().getId()));

        ItemPriceList itemPriceList = itemPriceListRepository.findByItemId(item.getId())
                .orElseThrow(() -> new RuntimeException("Item price not found for item id: " + item.getId()));

        CustomerOrder order = new CustomerOrder();
        order.setCustomerAccount(customer);
        order.setItem(item);
        order.setPrice(itemPriceList.getPrice()); // Set price from ItemPriceList
        order.setTransactionAllowed(true);
        order.setBillingProd(true);
        order.setIsTransactionProcessCompleted(false);

        return customerOrderRepository.save(order);
    }


}

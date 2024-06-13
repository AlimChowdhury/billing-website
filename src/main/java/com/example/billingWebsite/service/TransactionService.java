package com.example.billingWebsite.service;

import com.example.billingWebsite.model.CustomerAccount;
import com.example.billingWebsite.model.CustomerOrder;
import com.example.billingWebsite.model.Transaction;
import com.example.billingWebsite.repository.CustomerAccountRepository;
import com.example.billingWebsite.repository.CustomerOrderRepository;
import com.example.billingWebsite.repository.ItemRepository;
import com.example.billingWebsite.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final CustomerOrderRepository customerOrderRepository;

    private static CustomerAccountRepository customerAccountRepository;

    private final ItemRepository itemRepository;

    @Autowired
    public TransactionService(CustomerOrderRepository customerOrderRepository, CustomerAccountRepository customerAccountRepository,
                              ItemRepository itemRepository,TransactionRepository transactionRepository) {
        this.customerOrderRepository = customerOrderRepository;
        this.customerAccountRepository = customerAccountRepository;
        this.itemRepository = itemRepository;
        this.transactionRepository = transactionRepository;
    }



    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Optional<Transaction> findTransaction(Long id) {
        return transactionRepository.findById(id);
    }

}

package com.example.billingWebsite.controller;

import com.example.billingWebsite.model.CustomerAccount;
import com.example.billingWebsite.model.Transaction;
import com.example.billingWebsite.service.CustomerAccountService;
import com.example.billingWebsite.service.CustomerOrderService;
import com.example.billingWebsite.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer-accounts")
public class CustomerAccountController {
    private final CustomerAccountService customerAccountService;

    private final TransactionService transactionService;
    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerAccountController(CustomerAccountService customerAccountService, TransactionService transactionService, CustomerOrderService customerOrderService) {
        this.customerAccountService = customerAccountService;
        this.transactionService = transactionService;
        this.customerOrderService = customerOrderService;
    }

    @PostMapping
    public CustomerAccount createCustomerAccount(@RequestBody CustomerAccount account) {
        return customerAccountService.createCustomerAccount(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAccount> getCustomerAccount(@PathVariable Long id) {
        return customerAccountService.findCustomerAccount(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{customerId}/deposit")
    public ResponseEntity<String> deposit(@PathVariable Long customerId, @RequestBody Transaction transaction) {
        try {
            customerAccountService.deposit(customerId, transaction);
            return ResponseEntity.ok("Deposit successful");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{customerId}/expense")
    public ResponseEntity<String> processExpense(@PathVariable Long customerId, @RequestBody Transaction transaction) {
        try {
            customerAccountService.processExpense(customerId, transaction);
            return ResponseEntity.ok("Expense processed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    // Other endpoints
}


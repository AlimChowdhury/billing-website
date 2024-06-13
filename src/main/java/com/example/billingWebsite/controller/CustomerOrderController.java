package com.example.billingWebsite.controller;

import com.example.billingWebsite.model.CustomerAccount;
import com.example.billingWebsite.model.CustomerOrder;
import com.example.billingWebsite.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrder> getCustomerOrderAccount(@PathVariable Long id) {
        return customerOrderService.findCustomerOrderAccount(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/{customerId}/create")
    public ResponseEntity<CustomerOrder> createOrder(@PathVariable Long customerId, @RequestBody CustomerOrder orderRequest) {
        try {
            CustomerOrder createdOrder = customerOrderService.createOrder(customerId, orderRequest);
            return ResponseEntity.ok(createdOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}

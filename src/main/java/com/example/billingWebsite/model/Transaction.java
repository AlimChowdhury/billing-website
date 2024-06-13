package com.example.billingWebsite.model;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionType;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "ref_sales_id")
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "ref_cust_id")
    private CustomerAccount customerAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public Transaction() {
    }

    public Transaction(Long id, String transactionType, Double amount, CustomerOrder customerOrder, CustomerAccount customerAccount) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.customerOrder = customerOrder;
        this.customerAccount = customerAccount;
    }



}

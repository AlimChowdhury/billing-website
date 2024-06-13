package com.example.billingWebsite.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer price;
    private Boolean isTransactionAllowed;
    private Boolean isBillingProd;
    private Boolean isTransactionProcessCompleted;

    @ManyToOne
    @JoinColumn(name = "ref_cust_id")
    private CustomerAccount customerAccount;

    @ManyToOne
    @JoinColumn(name = "ref_item_id")
    private Item item;

    @OneToMany(mappedBy = "customerOrder")
    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getTransactionAllowed() {
        return isTransactionAllowed;
    }

    public void setTransactionAllowed(Boolean transactionAllowed) {
        isTransactionAllowed = transactionAllowed;
    }

    public Boolean getBillingProd() {
        return isBillingProd;
    }

    public void setBillingProd(Boolean billingProd) {
        isBillingProd = billingProd;
    }

    public Boolean getIsTransactionProcessCompleted() {
        return isTransactionProcessCompleted;
    }

    public void setIsTransactionProcessCompleted(Boolean transactionProcessCompleted) {
        isTransactionProcessCompleted = transactionProcessCompleted;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public CustomerOrder(Long id, Integer price, Boolean isTransactionAllowed, Boolean isBillingProd, Boolean isTransactionProcessCompleted, CustomerAccount customerAccount, Item item, List<Transaction> transactions) {
        this.id = id;
        this.price = price;
        this.isTransactionAllowed = isTransactionAllowed;
        this.isBillingProd = isBillingProd;
        this.isTransactionProcessCompleted = isTransactionProcessCompleted;
        this.customerAccount = customerAccount;
        this.item = item;
        this.transactions = transactions;
    }

    public CustomerOrder() {
    }
}
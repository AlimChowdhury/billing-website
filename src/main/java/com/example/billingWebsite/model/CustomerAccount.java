package com.example.billingWebsite.model;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class CustomerAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parentId;
    private String name;
    private Boolean isPostpaid;
    private Boolean isResellerBillingAllowed;
    private double allowedCreditAmount;

    @OneToMany(mappedBy = "customerAccount")
    private List<Transaction>transactions;

    @OneToMany(mappedBy = "customerAccount")
    private List<CustomerOrder>customerOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPostpaid() {
        return isPostpaid;
    }

    public void setPostpaid(Boolean postpaid) {
        isPostpaid = postpaid;
    }

    public Boolean getResellerBillingAllowed() {
        return isResellerBillingAllowed;
    }

    public void setResellerBillingAllowed(Boolean resellerBillingAllowed) {
        isResellerBillingAllowed = resellerBillingAllowed;
    }

    public double getAllowedCreditAmount() {
        return allowedCreditAmount;
    }

    public void setAllowedCreditAmount(double allowedCreditAmount) {
        this.allowedCreditAmount = allowedCreditAmount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public CustomerAccount() {
    }

    public CustomerAccount(Long id, Long parentId, String name, Boolean isPostpaid, Boolean isResellerBillingAllowed,
                           double allowedCreditAmount, List<Transaction> transactions, List<CustomerOrder> customerOrders) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.isPostpaid = isPostpaid;
        this.isResellerBillingAllowed = isResellerBillingAllowed;
        this.allowedCreditAmount = allowedCreditAmount;
        this.transactions = transactions;
        this.customerOrders = customerOrders;
    }

    @Override
    public String toString() {
        return "CustomerAccount{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", isPostpaid=" + isPostpaid +
                ", isResellerBillingAllowed=" + isResellerBillingAllowed +
                ", allowedCreditAmount=" + allowedCreditAmount +
                ", transactions=" + transactions +
                ", customerOrders=" + customerOrders +
                '}';
    }
}

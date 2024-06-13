package com.example.billingWebsite.model;

import jakarta.persistence.*;

@Entity
public class ItemPriceList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "ref_item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ref_cust_id")
    private CustomerAccount customerAccount;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public ItemPriceList() {
    }

    public ItemPriceList(Long id, Integer price, Item item, CustomerAccount customerAccount) {
        this.id = id;
        this.price = price;
        this.item = item;
        this.customerAccount = customerAccount;
    }
}

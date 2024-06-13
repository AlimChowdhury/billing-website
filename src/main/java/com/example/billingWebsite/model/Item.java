package com.example.billingWebsite.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "item")
    private List<ItemPriceList>itemPriceLists;

    @OneToMany(mappedBy = "item")
    private List<CustomerOrder>customerOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemPriceList> getItemPriceLists() {
        return itemPriceLists;
    }

    public void setItemPriceLists(List<ItemPriceList> itemPriceLists) {
        this.itemPriceLists = itemPriceLists;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public Item() {
    }

    public Item(Long id, String name, List<ItemPriceList> itemPriceLists, List<CustomerOrder> customerOrders) {
        this.id = id;
        this.name = name;
        this.itemPriceLists = itemPriceLists;
        this.customerOrders = customerOrders;
    }
}

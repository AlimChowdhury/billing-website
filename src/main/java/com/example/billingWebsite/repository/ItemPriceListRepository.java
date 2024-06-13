package com.example.billingWebsite.repository;

import com.example.billingWebsite.model.CustomerAccount;
import com.example.billingWebsite.model.Item;
import com.example.billingWebsite.model.ItemPriceList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemPriceListRepository extends JpaRepository<ItemPriceList,Long> {
    Optional<ItemPriceList> findByItemId(Long itemId);
}

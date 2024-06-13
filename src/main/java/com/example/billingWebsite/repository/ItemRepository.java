package com.example.billingWebsite.repository;

import com.example.billingWebsite.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}

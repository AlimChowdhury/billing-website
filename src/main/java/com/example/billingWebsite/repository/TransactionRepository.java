package com.example.billingWebsite.repository;

import com.example.billingWebsite.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}

package com.example.billingWebsite.repository;

import com.example.billingWebsite.model.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount,Long> {
}

package com.example.billingWebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BillingWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingWebsiteApplication.class, args);
	}

}

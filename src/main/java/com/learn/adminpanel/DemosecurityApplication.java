package com.learn.adminpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemosecurityApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemosecurityApplication.class, args);
	}
}
package com.example.pims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Amol.Sarwade
 */
@SpringBootApplication
@EnableJpaRepositories
public class ProductInfoManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductInfoManagementSystemApplication.class, args);
    }
}

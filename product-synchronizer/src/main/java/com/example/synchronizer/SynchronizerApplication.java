package com.example.synchronizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Amol.Sarwade
 */
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class SynchronizerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SynchronizerApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

package com.example.synchronizer.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Amol.Sarwade
 */
@Data
@Builder
public class Product {
    private String sku;
    private String title;
    private String description;
    private BigDecimal price;
    private int quantity;
}

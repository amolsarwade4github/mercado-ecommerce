package com.example.synchronizer.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Amol.Sarwade
 */
@Data
public class ProductInput {

    @NotEmpty(message = "sku is mandatory")
    private String sku;

    @NotEmpty(message = "title is mandatory")
    @Size(min = 2, message = "title must have min of 2 characters")
    private String title;

    private String description;

    @NotNull(message = "price is mandatory")
    @Range(min = 1, message = "price min range is 1")
    private String price;

    @NotNull(message = "quantity is mandatory")
    @Range(min = 0, message = "price min range is 0")
    private String quantity;
}

package com.example.pims.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Amol.Sarwade
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "sku is mandatory")
    private String sku;

    @NotEmpty(message = "title is mandatory")
    @Size(min = 2, message = "title must have min of 2 characters")
    private String title;

    private String description;

    @NotNull(message = "price is mandatory")
    @Range(min = 1, message = "price min range is 1")
    private BigDecimal price;

    @NotNull(message = "quantity is mandatory")
    @Range(min = 0, message = "price min range is 0")
    private int quantity;
}

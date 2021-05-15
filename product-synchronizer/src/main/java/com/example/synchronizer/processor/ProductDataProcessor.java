package com.example.synchronizer.processor;

import com.example.synchronizer.dto.Product;
import com.example.synchronizer.dto.ProductInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

/**
 * @author Amol.Sarwade
 */
public class ProductDataProcessor implements ItemProcessor<ProductInput, Product> {

    public static final Logger log = LoggerFactory.getLogger(ProductDataProcessor.class);

    @Override
    public Product process(ProductInput input) throws Exception {
        return Product.builder()
                .sku(input.getSku())
                .title(input.getTitle())
                .description(input.getDescription())
                .price(new BigDecimal(input.getPrice()))
                .quantity(Integer.parseInt(input.getQuantity())).build();
    }
}

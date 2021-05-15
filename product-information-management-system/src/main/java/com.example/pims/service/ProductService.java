package com.example.pims.service;

import com.example.pims.model.Product;

import java.util.List;

/**
 * @author Amol.Sarwade
 */
public interface ProductService {

    Iterable<Product> getAllProducts();

    Product getProductBySku(String sku);

    Product saveProduct(Product product);

    Iterable<Product> saveProducts(List<Product> products);

    void deleteProductBySku(String sku);

    Product updateProduct(Product product);

    int getSkuCount(String sku);

    boolean skuExists(String sku);
}

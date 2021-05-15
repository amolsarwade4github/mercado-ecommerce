package com.example.pims.service;

import com.example.pims.model.Product;
import com.example.pims.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Amol.Sarwade
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    @Override
    @Transactional(readOnly = false)
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = false)
    public Iterable<Product> saveProducts(List<Product> products) {
        List<Product> productsToSave = getNonExistingProducts(products);

        if (CollectionUtils.isEmpty(productsToSave)) {
            return new ArrayList<>();
        }

        return productRepository.saveAll(productsToSave);
    }

    @Override
    @Transactional(readOnly = false)
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProductBySku(String sku) {
        productRepository.deleteBySku(sku);
    }

    @Override
    public int getSkuCount(String sku) {
        return productRepository.getSkuCount(sku);
    }

    @Override
    public boolean skuExists(String sku) {
        return productRepository.getSkuCount(sku) > 0;
    }

    private List<Product> getNonExistingProducts(List<Product> products) {
        return products.stream()
                .filter(p -> !skuExists(p.getSku()))
                .collect(Collectors.toList());
    }
}

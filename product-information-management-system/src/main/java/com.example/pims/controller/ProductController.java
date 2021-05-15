package com.example.pims.controller;

import com.example.pims.dto.ApiResponse;
import com.example.pims.exception.ProductAlreadyExistsException;
import com.example.pims.exception.ProductNotFoundException;
import com.example.pims.model.Product;
import com.example.pims.service.ProductService;
import com.example.pims.util.ApiUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Amol.Sarwade
 */
@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ApiResponse getAllProducts() {
        Iterable<Product> allProducts = productService.getAllProducts();
        if (CollectionUtils.isEmpty((Collection<?>) allProducts)) {
            throw new ProductNotFoundException("Products not found");
        }
        return ApiUtility.buildResponse(true, allProducts);
    }

    @GetMapping(value = "{sku}")
    public ApiResponse getProduct(@PathVariable(value = "sku", required = true) String sku) {
        Product productBySku = productService.getProductBySku(sku);
        if (productBySku == null) {
            throw new ProductNotFoundException("Product with sku " + sku + " does not exists");
        }
        return ApiUtility.buildResponse(true, productBySku);
    }

    @PostMapping(value = "/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createProducts(@Valid @RequestBody List<Product> products) {
        Iterable<Product> savedProducts = productService.saveProducts(products);
        if (CollectionUtils.isEmpty((Collection<?>) savedProducts)) {
            throw new ProductAlreadyExistsException("Product with sku " + getSkus(products) + " already exists");
        }
        return ApiUtility.buildResponse(true, savedProducts, "Products created successfully");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createProduct(@Valid @RequestBody Product product) {
        Product saveProduct = productService.saveProduct(product);
        return ApiUtility.buildResponse(true, saveProduct, "Product created successfully");
    }

    @PutMapping(value = "{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse updateProduct(@PathVariable(value = "sku") String sku, @Valid @RequestBody Product product) {
        Product productBySku = productService.getProductBySku(sku);
        if (productBySku == null) {
            throw new ProductNotFoundException("Product with sku " + sku + " does not exists");
        }
        Product updateProduct = productService.updateProduct(product);
        return ApiUtility.buildResponse(true, updateProduct, "Product updated successfully");
    }

    @DeleteMapping(value = "{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deleteProductBySku(@PathVariable(value = "sku", required = true) String sku) {
        Product productBySku = productService.getProductBySku(sku);
        if (productBySku == null) {
            throw new ProductNotFoundException("Product with sku " + sku + " does not exists");
        }
        productService.deleteProductBySku(sku);
        return ApiUtility.buildResponse(true, "Product with sku " + sku + " is deleted successfully");
    }

    private String getSkus(List<Product> products) {
        return products.stream()
                .map(p -> p.getSku())
                .collect(Collectors.joining(","));
    }

}

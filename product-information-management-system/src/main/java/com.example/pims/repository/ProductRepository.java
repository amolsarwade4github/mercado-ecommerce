package com.example.pims.repository;

import com.example.pims.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

/**
 * @author Amol.Sarwade
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findBySku(String sku);

    void deleteBySku(String sku);

    @Query("select count(p.sku) from Product p where p.sku =:sku")
    int getSkuCount(@PathParam("sku") String sku);

}

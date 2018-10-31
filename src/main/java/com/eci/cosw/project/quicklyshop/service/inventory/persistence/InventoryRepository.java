package com.eci.cosw.project.quicklyshop.service.inventory.persistence;

import com.eci.cosw.project.quicklyshop.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface InventoryRepository extends MongoRepository<Product, String> {

    boolean existsProductById(String id);

    void deleteById(String id);

    Product findProductById(String id);

    Product save(Product product);

}

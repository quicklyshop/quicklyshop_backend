package com.eci.cosw.project.quicklyshop.security.service.inventory.persistence;

import com.eci.cosw.project.quicklyshop.security.model.Product;
import com.eci.cosw.project.quicklyshop.security.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {

    Product findProductById(String id);

    Product findProductByName(String name);

    boolean existsByName(String name);

    List<Product> findProductByPrice(double price);

    List<Product> findProductBySupplier(String supplier);

    Product save(Product product);



}

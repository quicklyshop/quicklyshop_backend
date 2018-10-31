package com.eci.cosw.project.quicklyshop.security.service.inventory.impl;

import com.eci.cosw.project.quicklyshop.security.model.Product;
import com.eci.cosw.project.quicklyshop.security.service.inventory.InventoryService;
import com.eci.cosw.project.quicklyshop.security.service.inventory.exceptions.InventoryServiceException;
import com.eci.cosw.project.quicklyshop.security.service.inventory.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {

    private long actual_id;

    @Autowired
    ProductRepository productRepository;

    public InventoryServiceImpl() {
        actual_id = 0L;
    }

    private void addProductWithId(Product product) {
        String newId = Long.toString(actual_id++);

        product.setId(newId);
        productRepository.save(product);
    }

    @Override
    public void addProduct(Product product, int quantity) throws InventoryServiceException {
        if (product == null) {
            throw new NullPointerException();
        }

        if (quantity <= 0) {
            throw new InventoryServiceException("La cantidad de productos a agregar al inventario no puede ser menor o igual a cero");
        }

        product.setQuantity(quantity);
        
        productRepository.save(product);
   }

    @Override
    public void removeProductById(String id) throws InventoryServiceException { if (id == null) {
            throw new NullPointerException();
        }
        productRepository.deleteById(id);
    }
    

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) throws InventoryServiceException {
        if (id == null) {
            throw new NullPointerException();
        }
        
        return productRepository.findProductById(id);
    }
}
package com.eci.cosw.project.quicklyshop.service.inventory.impl;

import com.eci.cosw.project.quicklyshop.model.Product;
import com.eci.cosw.project.quicklyshop.service.inventory.InventoryService;
import com.eci.cosw.project.quicklyshop.service.inventory.exceptions.InventoryServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceDummy implements InventoryService {

    private long actual_id;

    private Map<String, Product> products;

    public InventoryServiceDummy() {
        actual_id = 0L;
        products = new HashMap<>();
    }

    private void addProductWithId(Product product) {
        String newId = Long.toString(actual_id++);

        product.setId(newId);
        products.put(newId, product);
    }

    @Override
    public void addProduct(Product product, int quantity) throws InventoryServiceException {
        if (product == null) {
            throw new NullPointerException();
        }

        if (quantity <= 0) {
            throw new InventoryServiceException("La cantidad de productos a agregar al inventario no puede ser menor o igual a cero");
        }

        for (int i = 0; i < quantity; i++) {
            addProductWithId(product);
        }
    }

    @Override
    public void removeProductById(String id) throws InventoryServiceException {
        if (id == null) {
            throw new NullPointerException();
        }

        if (!products.containsKey(id)) {
            throw new InventoryServiceException("No existe el producto con ese id");
        }

        products.remove(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductById(String id) throws InventoryServiceException {
        if (id == null) {
            throw new NullPointerException();
        }

        if (!products.containsKey(id)) {
            throw new InventoryServiceException("No existe el producto con ese id");
        }

        return products.get(id);
    }
}

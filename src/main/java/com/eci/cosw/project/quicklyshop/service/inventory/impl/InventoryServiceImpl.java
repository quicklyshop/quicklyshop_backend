package com.eci.cosw.project.quicklyshop.service.inventory.impl;

import com.eci.cosw.project.quicklyshop.model.Product;
import com.eci.cosw.project.quicklyshop.service.inventory.InventoryService;
import com.eci.cosw.project.quicklyshop.service.inventory.exceptions.InventoryServiceException;
import com.eci.cosw.project.quicklyshop.service.inventory.persistence.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public void addProduct(Product product, int quantity) throws InventoryServiceException {
        if (product == null) {
            throw new NullPointerException();
        }

        if (quantity <= 0) {
            throw new InventoryServiceException("La cantidad de productos a agregar al inventario no puede ser menor o igual a cero");
        }

        for (int i = 0; i < quantity; i++) {
            inventoryRepository.save(product);
        }
    }

    @Override
    public void removeProductById(String id) throws InventoryServiceException {
        if (!inventoryRepository.existsProductById(id)) {
            throw new InventoryServiceException("No existe el producto con ese id");
        }

        inventoryRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return inventoryRepository.findAll();
    }

    @Override
    public Product getProductById(String id) throws InventoryServiceException {
        if (!inventoryRepository.existsProductById(id)) {
            throw new InventoryServiceException("No existe el producto con ese id");
        }

        return inventoryRepository.findProductById(id);
    }
}

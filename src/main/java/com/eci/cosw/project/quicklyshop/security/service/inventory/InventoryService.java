package com.eci.cosw.project.quicklyshop.security.service.inventory;

import com.eci.cosw.project.quicklyshop.security.model.Product;
import com.eci.cosw.project.quicklyshop.security.service.inventory.exceptions.InventoryServiceException;

import java.util.List;

public interface InventoryService {

    /**
     * Anade nuevos productos al catalogo de productos
     * @param product producto que se quiere agregar
     * @param quantity cantidad del producto que se anadira
     * @throws InventoryServiceException la cantidad es menor o igual a cero
     * @throws NullPointerException el producto es null
     */
    void addProduct(Product product, int quantity) throws InventoryServiceException;

    /**
     * Remueve un producto basado en su id
     * @param id identificador unico del producto
     * @throws InventoryServiceException el producto no existe
     * @throws NullPointerException el id es null
     */
    void removeProductById(String id) throws InventoryServiceException;

    /**
     * Obtiene todos los productos registrados
     * @return productos
     */
    List<Product> getAllProducts();

    /**
     * Obtiene un producto basado en su id
     * @param id identificador del producto
     * @return producto
     * @throws InventoryServiceException el producto no existe / esta agotado
     * @throws NullPointerException el id es null
     */
    Product getProductById(String id) throws InventoryServiceException;

}

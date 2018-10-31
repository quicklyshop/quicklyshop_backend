package com.eci.cosw.project.quicklyshop.service.product;

import com.eci.cosw.project.quicklyshop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();

    void addProduct(Product product);
}

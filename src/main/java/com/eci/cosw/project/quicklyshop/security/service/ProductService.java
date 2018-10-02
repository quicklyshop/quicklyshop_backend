package com.eci.cosw.project.quicklyshop.security.service;

import com.eci.cosw.project.quicklyshop.security.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();
    void addProduct( Product product );
}

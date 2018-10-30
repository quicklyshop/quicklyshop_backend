package com.eci.cosw.project.quicklyshop.security.service.product.impl;

import com.eci.cosw.project.quicklyshop.security.model.Product;
import com.eci.cosw.project.quicklyshop.security.service.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<>();
    }

    @Override
    public List<Product> getProductList() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }
}

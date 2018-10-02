package com.eci.cosw.project.quicklyshop.security.service;

import com.eci.cosw.project.quicklyshop.security.model.Product;
import com.eci.cosw.project.quicklyshop.security.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private List<Product> products = new ArrayList<>();

    @Autowired
    public ProductServiceImpl()
    {
    }


    @Override
    public List<Product> getProductList() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        product.add(product);

    }
}

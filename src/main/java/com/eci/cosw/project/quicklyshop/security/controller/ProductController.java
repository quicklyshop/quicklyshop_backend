package com.eci.cosw.project.quicklyshop.security.controller;

import com.eci.cosw.project.quicklyshop.security.model.Product;
import com.eci.cosw.project.quicklyshop.security.service.ProductService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "api" )
public class ProductController
{

    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping( value = "/products", method = RequestMethod.GET )
    public List<Product> getProductList(){
        return productService.getProductList();
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping( value = "/products", method = RequestMethod.POST )
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

}

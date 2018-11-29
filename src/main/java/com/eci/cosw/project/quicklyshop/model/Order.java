package com.eci.cosw.project.quicklyshop.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Order {

    @Id
    private String id;

    private List<Product> products;

    private User user;

    private boolean payment;

    public Order() {
        this.products = new ArrayList<>();
        this.user = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
}

package com.eci.cosw.project.quicklyshop.model;

public class Payment {

    private String id;
    private User user;
    private Order order;
    private String paymentMethod;

    public Payment(String id, User user, Order order, String paymentMethod) {
        this.id = id;
        this.user = user;
        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

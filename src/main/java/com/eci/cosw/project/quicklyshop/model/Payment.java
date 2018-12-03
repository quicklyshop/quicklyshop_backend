package com.eci.cosw.project.quicklyshop.model;

import java.util.Date;

public class Payment {

    private String id;
    private User user;
    private Order order;
    private Date date;

    public Payment(String id, User user, Order order) {
        this.id = id;
        this.user = user;
        this.order = order;
        this.date = new Date();
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

    public Date getDatePayment() {
        return date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", order='" + order + '\'' +
                ", DatePayment='" + date + '\'' +
                '}';
    }
}

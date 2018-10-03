package com.eci.cosw.project.quicklyshop.security.model;

public class Product {

    private long id;

    private String name;

    private double price;

    private String description;

    private String supplier;


    public Product() {
        this("", 0.0, "", "");
    }

    public Product(String name, double price, String description, String supplier) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.supplier = supplier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}

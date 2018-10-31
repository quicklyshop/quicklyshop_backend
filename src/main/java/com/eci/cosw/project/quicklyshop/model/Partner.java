package com.eci.cosw.project.quicklyshop.model;

import org.springframework.data.annotation.Id;

public class Partner {

    @Id
    private String id;

    private String ident;

    private String identType;

    private String name;

    private String address;

    private String phone;

    public Partner() {
        this.id = "";
        this.ident = "";
        this.identType = "";
        this.name = "";
        this.address = "";
        this.phone = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getIdentType() {
        return identType;
    }

    public void setIdentType(String identType) {
        this.identType = identType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

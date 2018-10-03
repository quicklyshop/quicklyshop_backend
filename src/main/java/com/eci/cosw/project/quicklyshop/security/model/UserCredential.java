package com.eci.cosw.project.quicklyshop.security.model;

public class UserCredential {

    private String hashedPassword;

    private String hashFunction;

    public UserCredential(String hashedPassword, String hashFunction) {
        this.hashedPassword = hashedPassword;
        this.hashFunction = hashFunction;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getHashFunction() {
        return hashFunction;
    }

    public void setHashFunction(String hashFunction) {
        this.hashFunction = hashFunction;
    }
}

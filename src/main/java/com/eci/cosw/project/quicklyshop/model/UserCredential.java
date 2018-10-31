package com.eci.cosw.project.quicklyshop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserCredential {
    
    @Id
    private String id;
    
    private String userName;

    private String hashedPassword;

    private String hashFunction;

    public UserCredential(String userName, String hashedPassword, String hashFunction) {
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.hashFunction = hashFunction;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
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
    
    @Override
    public String toString() {
        return "UserCredential{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", hashFunction='" + hashFunction + '\'' +
                '}';
    }
}

package com.eci.cosw.project.quicklyshop.security.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Token {
    
    @Id
    String id;
    
    String userName;
    
    String accessToken;

    Date creationTime;

    public Token(String access_token) {
        this.accessToken = access_token;
        this.creationTime = new Date();
    }

    public Token(String userName, String access_token, Date date) {
        this.userName = userName;
        this.accessToken = access_token;
        this.creationTime = date;
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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String access_token) {
        this.accessToken = access_token;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", token='" + accessToken + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }
}
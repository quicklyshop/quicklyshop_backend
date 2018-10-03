package com.eci.cosw.project.quicklyshop.security.model;

import java.util.Date;

public class Token {
    String accessToken;

    Date creationTime;

    public Token(String access_token) {
        this.accessToken = access_token;
        this.creationTime = new Date();
    }

    public Token(String access_token, Date date) {
        this.accessToken = access_token;
        this.creationTime = date;
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
}
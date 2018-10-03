package com.eci.cosw.project.quicklyshop.security.service;

import com.eci.cosw.project.quicklyshop.security.model.User;

import java.util.List;

public interface UserService
{
    List<User> getUsers();

    User getUser( String username );

    User createUser( User user );

    User findUserByEmail( String email );

    User findUserByEmailAndPassword( String email, String password );
}
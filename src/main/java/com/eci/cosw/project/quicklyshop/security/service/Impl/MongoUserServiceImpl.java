package com.eci.cosw.project.quicklyshop.security.service.Impl;

import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.service.UserService;
import com.eci.cosw.project.quicklyshop.security.service.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class MongoUserServiceImpl implements UserService {
    private List<User> users;

    @Autowired
    private UserRepository userRepository;

    public MongoUserServiceImpl() {
        users = new ArrayList<>();
    }



    @Override
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User getUser( String username )
    {
        return   userRepository.findUserByEmail(username) ;
    }

    @Override
    public User createUser( User user )
    {
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail( String email )
    {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByEmailAndPassword( String email, String password )
    {
        return users.get( 0 );
    }
}

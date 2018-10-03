package com.eci.cosw.project.quicklyshop.security.service.Impl;

import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private List<User> users;

    public UserServiceImpl() {
        users = new ArrayList<>();
    }

    @PostConstruct
    private void populateSampleData() {
        users.add( new User( "test@mail.com", "Andres", "Perez" , "andres-perez") );
    }

    @Override
    public List<User> getUsers()
    {
        return users;
    }

    @Override
    public User getUser( String username )
    {
        return users.get( 0 );
    } // TODO

    @Override
    public User createUser( User user )
    {
        return users.get( 0 );
    } // TODO

    @Override
    public User findUserByEmail( String email )
    {
        return users.get( 0 );
    } // TODO

    @Override
    public User findUserByEmailAndPassword( String email, String password )
    {
        return users.get( 0 );
    } // TODO

}

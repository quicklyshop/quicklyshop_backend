package com.eci.cosw.project.quicklyshop.security.service.user.impl;

import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.service.user.UserService;
import com.eci.cosw.project.quicklyshop.security.service.user.exceptions.UserServiceException;
import com.eci.cosw.project.quicklyshop.security.service.user.persistence.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    private void populateSampleData() { // TODO: borrar esto
        User n = new User("test@mail.com", "Andres", "Perez Ortiz", "andres-perez", "3121234567", "Cr. 1 # 1 - 1");

        try {
            User res = createUser(n);
        } catch (UserServiceException e) {
            logger.error("El usuario de prueba ya existe.");
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User createUser(User user) throws UserServiceException {
        logger.debug("Registering user: {}", user.toString());

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserServiceException("El usuario ya existe.");
        }

        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}

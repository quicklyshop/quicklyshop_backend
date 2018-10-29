package com.eci.cosw.project.quicklyshop.security.service.profile.impl;

import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.service.profile.ProfileService;
import com.eci.cosw.project.quicklyshop.security.service.profile.exceptions.ProfileServiceException;
import com.eci.cosw.project.quicklyshop.security.service.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void updateUserProfile(User user) throws ProfileServiceException {
        if(user == null) {
            throw new NullPointerException("El usuario indicado es nulo");
        }

        if (!userRepository.existsByUsername(user.getUsername())) {
            throw new ProfileServiceException("El usuario no existe en la BD");
        }

        userRepository.save(user);
    }

    @Override
    public User getUserProfile(String username) throws ProfileServiceException {
        if (!userRepository.existsByUsername(username)) {
            throw new ProfileServiceException("El usuario no existe en la BD");
        }

        return userRepository.findUserByUsername(username);
    }
}

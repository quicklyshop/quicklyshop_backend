package com.eci.cosw.project.quicklyshop.security.service;

import com.eci.cosw.project.quicklyshop.security.model.User;

import java.util.List;

public interface UserService
{
    List<User> getUsers();

    /**
     * Obtiene un usuario
     * @param username nombre del usuario
     * @return usuario
     * @throws UserServiceException el usuario no existe
     */
    User getUser( String username ) throws UserServiceException;

    /**
     * Crea un usuario nuevo
     * @param user usuario
     * @return usuario creado
     * @throws UserServiceException ya existe el usuario
     */
    User createUser( User user ) throws UserServiceException;

    User findUserByEmail( String email );

    User findUserByEmailAndPassword( String email, String password );
}
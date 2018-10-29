package com.eci.cosw.project.quicklyshop.security.service.user;

import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.service.user.exceptions.UserServiceException;

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

    User findUserByEmail( String email ); // XXX: por que esta aqui?
}
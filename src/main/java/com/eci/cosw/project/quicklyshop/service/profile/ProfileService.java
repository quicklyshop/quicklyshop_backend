package com.eci.cosw.project.quicklyshop.service.profile;

import com.eci.cosw.project.quicklyshop.model.User;
import com.eci.cosw.project.quicklyshop.service.profile.exceptions.ProfileServiceException;

public interface ProfileService {

    /**
     * Actualiza el perfil que corresponde al usuario indicado
     * @param user parametros completos del usuario
     * @throws ProfileServiceException el nombre de usuario no existe en la BD
     * @throws NullPointerException el usuario es nulo
     */
    void updateUserProfile(User user) throws ProfileServiceException;

    /**
     * Obtiene el usuario en base al nombre de usuario que tenga
     * @param username nombre de usuario
     * @return el usuario con sus parametros registrados
     * @throws ProfileServiceException no existe ningun usuario con ese nombre de usuario
     * @throws NullPointerException el string es nulo
     */
    User getUserProfile(String username) throws ProfileServiceException;
}

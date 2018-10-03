package com.eci.cosw.project.quicklyshop.security.service;

import com.eci.cosw.project.quicklyshop.security.digestfunctions.DigestFunction;
import com.eci.cosw.project.quicklyshop.security.model.UserCredential;

public interface UserCredentialService {

    /**
     * Retorna las credenciales de un usuario
     *
     * @param username nombre de usuario
     * @return credenciales de usuario
     * @throws UserCredentialServiceException el usuario no existe
     * @throws NullPointerException           si username es null
     */
    UserCredential getUserCredential(String username) throws UserCredentialServiceException;


    /**
     * Registra nuevas credenciales para un usuario, si ya habian credenciales registradas para ese usuario entonces se
     * reemplazan con las nuevas
     *
     * @param username    nombre de usuario
     * @param credentials credenciales para registrar
     * @throws NullPointerException el nombre de usuario o las credenciales son nulas
     */
    void registerCredentials(String username, UserCredential credentials) throws NullPointerException;

    /**
     * Da la clase de funcion de digest para calcular el hash
     * @param digestFunctionName nombre de funcion de hash
     * @return la funcion de hash
     * @throws UserCredentialServiceException la funcion hash no existe
     * @throws NullPointerException el nombre es nulo
     */
    DigestFunction getDigestFunction(String digestFunctionName);

}

package com.eci.cosw.project.quicklyshop.security.service.usercredential;

import com.eci.cosw.project.quicklyshop.security.model.Token;

public interface TokenService {

    /**
     * Genera un nuevo token para un usuario
     * @param username nombre del usuario
     * @return token
     */
    Token generateToken(String username, String password);

    /**
     * Checkea que el token sea valido y que le pertenezca al usuario
     * @param token token
     * @param username nombre del usuario
     * @return es valido
     */
    boolean validTokenForUser(Token token, String username);

    /**
     * Checkea que el token sea valido
     * @param token token
     * @return es valido
     */
    boolean validToken(Token token);
}

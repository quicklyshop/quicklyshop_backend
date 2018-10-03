package com.eci.cosw.project.quicklyshop.security.digestfunctions;

public interface DigestFunction {

    /**
     * el hash de la contrasena coincide con el hash especificado
     * @param rawPassword contrasena original
     * @param encodedPassword contrasena codificada a comparar
     * @return coinciden
     * @throws NullPointerException algun argumento es nulo
     */
    boolean matches(CharSequence rawPassword, CharSequence encodedPassword) throws NullPointerException;

    /**
     * Codifica una contrasena de entrada a su hash
     * @param rawPassword contrasena
     * @return hash de la contrasena
     * @throws NullPointerException la contrasena es nula
     */
    CharSequence encode(CharSequence rawPassword) throws NullPointerException;
}

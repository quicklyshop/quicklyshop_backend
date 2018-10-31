package com.eci.cosw.project.quicklyshop.digestfunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Sha1DigestFunction implements DigestFunction {

    private static final Logger logger = LogManager.getLogger(Sha1DigestFunction.class);

    @Override
    public boolean matches(CharSequence rawPassword, CharSequence encodedPassword) throws NullPointerException {
        return encode(rawPassword).equals(encodedPassword);
    }

    @Override
    public CharSequence encode(CharSequence rawPassword) throws NullPointerException {
        byte[] byteCypherText = rawPassword.toString().getBytes();

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new NullPointerException(e.getMessage());
        }
        byte[] digest = md.digest(byteCypherText);
        String sha1 = Base64.getEncoder().encodeToString(digest);

        logger.debug("Cypher text SHA1 hash: \'{}\'", sha1);

        return sha1;
    }
}

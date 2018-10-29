package com.eci.cosw.project.quicklyshop.security.service.usercredential.impl;

import com.eci.cosw.project.quicklyshop.security.digestfunctions.DigestFunction;
import com.eci.cosw.project.quicklyshop.security.digestfunctions.RawDigestFunction;
import com.eci.cosw.project.quicklyshop.security.model.UserCredential;
import com.eci.cosw.project.quicklyshop.security.service.usercredential.UserCredentialService;
import com.eci.cosw.project.quicklyshop.security.service.usercredential.exceptions.UserCredentialServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserCredentialServiceImplDummy implements UserCredentialService {

    public static final String CURRENT_HASH_METHOD = "Sha1";

    private Map<String, UserCredential> credentials;

    public UserCredentialServiceImplDummy() {
        credentials = new HashMap<>();
    }

    @PostConstruct
    public void populateDummyData() {
        UserCredential uc = new UserCredential("andres-perez", "password", "Raw");
        registerCredentials("andres-perez", uc);
    }

    @Override
    public UserCredential getUserCredential(String username) throws UserCredentialServiceException {
        if (!credentials.containsKey(username)) {
            throw new UserCredentialServiceException("El usuario no tiene credenciales registradas");
        }

        return credentials.get(username);
    }

    @Override
    public void registerCredentials(String username, UserCredential userCredentials) throws NullPointerException {
        if(username == null || userCredentials == null) {
            throw new NullPointerException("El usuario o las credenciales son nulas");
        }

        credentials.put(username, userCredentials);
    }

    @Override
    public void registerPasswordCredentials(String username, String rawPassword) throws NullPointerException {
        DigestFunction dfunc = null;
        try {
            dfunc = getDigestFunction(CURRENT_HASH_METHOD);
        } catch (UserCredentialServiceException e) {
            throw new NullPointerException(e.getMessage());
        }
        UserCredential userCredential = new UserCredential(username, String.valueOf(dfunc.encode(rawPassword)), CURRENT_HASH_METHOD);
        registerCredentials(username, userCredential);
    }

    @Override
    public DigestFunction getDigestFunction(String digestFunctionName) throws UserCredentialServiceException {
        DigestFunction res;

        try {
            Class clazz = Class.forName("com.eci.cosw.project.quicklyshop.security.digestfunctions." + digestFunctionName + "DigestFunction");
            res = (DigestFunction) clazz.newInstance();
        } catch (Exception e) {
            throw new UserCredentialServiceException("Funcion de digest no existe: " + digestFunctionName);
        }

        System.out.println(res.getClass());

        return res;
    }
}

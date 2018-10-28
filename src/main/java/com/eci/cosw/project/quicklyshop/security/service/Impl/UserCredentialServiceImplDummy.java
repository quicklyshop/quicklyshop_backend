package com.eci.cosw.project.quicklyshop.security.service.Impl;

import com.eci.cosw.project.quicklyshop.security.digestfunctions.DigestFunction;
import com.eci.cosw.project.quicklyshop.security.digestfunctions.DummyDigestFunction;
import com.eci.cosw.project.quicklyshop.security.model.UserCredential;
import com.eci.cosw.project.quicklyshop.security.service.UserCredentialService;
import com.eci.cosw.project.quicklyshop.security.service.UserCredentialServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


public class UserCredentialServiceImplDummy implements UserCredentialService {

    public static final String CURRENT_HASH_METHOD = "raw"; // TODO

    private Map<String, UserCredential> credentials;

    public UserCredentialServiceImplDummy() {
        credentials = new HashMap<>();
    }

    @PostConstruct
    public void populateDummyData() {
        UserCredential uc = new UserCredential("userName", "password", "raw");
        credentials.put("andres-perez", uc);
    }

    @Override
    public UserCredential getUserCredential(String username) throws UserCredentialServiceException {
        if (!credentials.containsKey(username)) {
            throw new UserCredentialServiceException("El usuario no tiene credenciales registradas");
        }

        return credentials.get(username);
    }

    @Override
    public void registerPasswordCredentials(String username, String rawPassword) throws NullPointerException {
        DigestFunction dfunc = getDigestFunction(CURRENT_HASH_METHOD);
        UserCredential userCredential = new UserCredential(username, String.valueOf(dfunc.encode(rawPassword)), CURRENT_HASH_METHOD);
        credentials.put(username, userCredential);
    }

    @Override
    public DigestFunction getDigestFunction(String digestFunctionName) {
        return new DummyDigestFunction();
    }
}

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

@Service
public class UserCredentialServiceImplDummy implements UserCredentialService {

    private Map<String, UserCredential> credentials;

    public UserCredentialServiceImplDummy() {
        credentials = new HashMap<>();
    }

    @PostConstruct
    public void populateDummyData() {
        UserCredential uc = new UserCredential("password", "raw");
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
    public DigestFunction getDigestFunction(String digestFunctionName) {
        return new DummyDigestFunction();
    }
}

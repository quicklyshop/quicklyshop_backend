package com.eci.cosw.project.quicklyshop.security.service.Impl;

import com.eci.cosw.project.quicklyshop.security.digestfunctions.DigestFunction;
import com.eci.cosw.project.quicklyshop.security.digestfunctions.DummyDigestFunction;
import com.eci.cosw.project.quicklyshop.security.model.UserCredential;
import com.eci.cosw.project.quicklyshop.security.service.UserCredentialService;
import com.eci.cosw.project.quicklyshop.security.service.UserCredentialServiceException;
import com.eci.cosw.project.quicklyshop.security.service.persistence.UserCredentialRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public class MongoUserCredentialServiceImpl implements UserCredentialService {

    UserCredentialRepository userCredentialRepository;

    public static final String CURRENT_HASH_METHOD = "raw"; // TODO

    private Map<String, UserCredential> credentials;

    public void MongoUserCredentialServiceImpl() {
        credentials = new HashMap<>();
    }

    @Override
    public UserCredential getUserCredential(String username) throws UserCredentialServiceException {
        return userCredentialRepository.findUserCredentialByUserName(username);
    }

    @Override
    public void registerCredentials(String username, UserCredential userCredentials) throws NullPointerException {
        userCredentials.setUserName(username);
        userCredentialRepository.save(userCredentials);
    }

    @Override
    public void registerPasswordCredentials(String username, String rawPassword) throws NullPointerException {
        DigestFunction dfunc = getDigestFunction(CURRENT_HASH_METHOD);
        UserCredential userCredential = new UserCredential(username, String.valueOf(dfunc.encode(rawPassword)), CURRENT_HASH_METHOD);
        registerCredentials(username,userCredential);
    }

    @Override
    public DigestFunction getDigestFunction(String digestFunctionName) {
        return new DummyDigestFunction();
    }

}

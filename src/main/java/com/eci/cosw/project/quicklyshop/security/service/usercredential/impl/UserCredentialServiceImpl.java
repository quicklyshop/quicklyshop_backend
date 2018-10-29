package com.eci.cosw.project.quicklyshop.security.service.usercredential.impl;

import com.eci.cosw.project.quicklyshop.security.digestfunctions.DigestFunction;
import com.eci.cosw.project.quicklyshop.security.model.UserCredential;
import com.eci.cosw.project.quicklyshop.security.service.usercredential.UserCredentialService;
import com.eci.cosw.project.quicklyshop.security.service.usercredential.exceptions.UserCredentialServiceException;
import com.eci.cosw.project.quicklyshop.security.service.usercredential.persistence.UserCredentialRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {

    public static final String CURRENT_HASH_METHOD = "Sha1";

    private static final Logger logger = LogManager.getLogger(UserCredentialServiceImpl.class);

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @PostConstruct
    public void populateDummyData() {
        DigestFunction dfunc = null;
        try {
            dfunc = getDigestFunction(CURRENT_HASH_METHOD);
        } catch (UserCredentialServiceException e) {
            throw new NullPointerException(e.getMessage());
        }

        UserCredential uc = new UserCredential(
                "andres-perez",
                String.valueOf(dfunc.encode("password")),
                CURRENT_HASH_METHOD);

        logger.debug("La contrasena del usuario de prueba en hash es: \'{}\'", uc.getHashedPassword());

        if (!userCredentialRepository.existsByUserName(uc.getUserName())) {
            registerCredentials(uc.getUserName(), uc);
            logger.info("Las credenciales del usuario de prueba fueron registradas");
        } else {
            logger.error("Credenciales de usuario de prueba ya estan registrados");
        }
    }

    @Override
    public UserCredential getUserCredential(String username) throws UserCredentialServiceException {
        if (!userCredentialRepository.existsByUserName(username)) {
            throw new UserCredentialServiceException("El usuario no tiene credenciales registradas");
        }

        return userCredentialRepository.findUserCredentialByUserName(username);
    }

    @Override
    public void registerCredentials(String username, UserCredential userCredentials) throws NullPointerException {
        if (username == null || userCredentials == null) {
            throw new NullPointerException("El usuario o las credenciales son nulas");
        }

        if (userCredentialRepository.existsByUserName(username)) {
            UserCredential uc = userCredentialRepository.findUserCredentialByUserName(username);
            userCredentialRepository.deleteById(uc.getId());
        }

        userCredentialRepository.save(userCredentials);
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

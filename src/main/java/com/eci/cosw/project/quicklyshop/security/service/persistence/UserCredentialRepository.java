package com.eci.cosw.project.quicklyshop.security.service.persistence;

import com.eci.cosw.project.quicklyshop.security.model.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCredentialRepository extends MongoRepository<UserCredential, String> {
    
    UserCredential findUserCredentialById(String id);
    
    UserCredential findUserCredentialByUserName(String userName);
    
}

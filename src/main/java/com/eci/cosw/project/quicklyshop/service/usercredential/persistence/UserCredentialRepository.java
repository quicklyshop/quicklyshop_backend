package com.eci.cosw.project.quicklyshop.service.usercredential.persistence;

import com.eci.cosw.project.quicklyshop.model.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCredentialRepository extends MongoRepository<UserCredential, String> {
    
    UserCredential findUserCredentialById(String id);
    
    UserCredential findUserCredentialByUserName(String userName);

    boolean existsByUserName(String username);
    
}

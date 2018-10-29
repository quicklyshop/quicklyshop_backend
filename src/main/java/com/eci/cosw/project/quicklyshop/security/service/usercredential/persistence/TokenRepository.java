package com.eci.cosw.project.quicklyshop.security.service.usercredential.persistence;

import com.eci.cosw.project.quicklyshop.security.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String> {
    
    Token findTokenById(String id);
    
    Token findTokenByUserName(String userName);
            
}

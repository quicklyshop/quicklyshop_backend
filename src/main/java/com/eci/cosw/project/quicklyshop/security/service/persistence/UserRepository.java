package com.eci.cosw.project.quicklyshop.security.service.persistence;

import com.eci.cosw.project.quicklyshop.security.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}

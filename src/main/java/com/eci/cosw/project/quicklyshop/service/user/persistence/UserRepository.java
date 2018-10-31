package com.eci.cosw.project.quicklyshop.service.user.persistence;

import com.eci.cosw.project.quicklyshop.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserById(String id);

    User findUserByUsername(String username);

    boolean existsByUsername(String username);

    User findUserByEmail(String email);
    
    List<User> findUserByFirstname(String firstname);
    
    List<User> findUserByLastname(String lastname);

    List<User> findUserByPhone(String phone);

    List<User> findUserByAddress(String address);

    User save(User user);

}

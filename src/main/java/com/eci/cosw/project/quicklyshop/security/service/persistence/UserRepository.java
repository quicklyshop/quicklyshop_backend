package com.eci.cosw.project.quicklyshop.security.service.persistence;

import com.eci.cosw.project.quicklyshop.security.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserById(long id);

    User findUserByEmail(String email);

    List<User> findUserByFirstName(String firstName);

    List<User> findUserBySecondName(String secondName);

    List<User> findUserByPhone(String phone);

    List<User> findUserByAddress(String address);

}

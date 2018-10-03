package com.eci.cosw.project.quicklyshop.security.controller;

import com.eci.cosw.project.quicklyshop.security.digestfunctions.DigestFunction;
import com.eci.cosw.project.quicklyshop.security.model.RegistrationForm;
import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.model.UserCredential;
import com.eci.cosw.project.quicklyshop.security.model.UserLogin;
import com.eci.cosw.project.quicklyshop.security.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    UserCredentialService credentialService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserLogin login) throws ServletException, UserCredentialServiceException {
        logger.debug("Access token requested by: \"{}\"", login.getUsername());

        if (login.getUsername() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        String username = login.getUsername();
        String password = login.getPassword();

        User user = null;

        try {
            user = userService.getUser(username);
        } catch (UserServiceException ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }

        if (user == null) {
            throw new ServletException("User username not found.");
        }

        UserCredential uCreds = credentialService.getUserCredential(user.getUsername());
        DigestFunction func = credentialService.getDigestFunction(uCreds.getHashFunction());

        if (!func.matches(password, uCreds.getHashedPassword())) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }

        logger.debug("Access token granted to: \"{}\"", login.getUsername());
        return new ResponseEntity<>(tokenService.generateToken(username, password), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationForm reg) {
        if (!reg.getUser().getUsername().equals(reg.getUserLogin().getUsername())) {
            String msg = "El usuario de logueo y de datos no coinciden";
            logger.error(msg);
            return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
        }

        try {
            userService.createUser(reg.getUser());
            credentialService.registerPasswordCredentials(reg.getUserLogin().getUsername(), reg.getUserLogin().getPassword());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserServiceException ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

}

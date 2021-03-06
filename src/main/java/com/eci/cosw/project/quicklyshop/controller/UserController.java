package com.eci.cosw.project.quicklyshop.controller;

import com.eci.cosw.project.quicklyshop.digestfunctions.DigestFunction;
import com.eci.cosw.project.quicklyshop.model.RegistrationForm;
import com.eci.cosw.project.quicklyshop.model.User;
import com.eci.cosw.project.quicklyshop.model.UserCredential;
import com.eci.cosw.project.quicklyshop.model.UserLogin;
import com.eci.cosw.project.quicklyshop.service.profile.ProfileService;
import com.eci.cosw.project.quicklyshop.service.profile.exceptions.ProfileServiceException;
import com.eci.cosw.project.quicklyshop.service.usercredential.exceptions.UserCredentialServiceException;
import com.eci.cosw.project.quicklyshop.service.user.exceptions.UserServiceException;
import com.eci.cosw.project.quicklyshop.service.user.UserService;
import com.eci.cosw.project.quicklyshop.service.usercredential.TokenService;
import com.eci.cosw.project.quicklyshop.service.usercredential.UserCredentialService;
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
    private UserCredentialService credentialService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    ProfileService profileService;

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
        logger.debug("Entra a registrar usuario");
        if (!reg.getUser().getUsername().equals(reg.getUserLogin().getUsername())) {
            String msg = "El usuario de logueo y de datos no coinciden";
            logger.error(msg);
            return new ResponseEntity<>(msg, HttpStatus.FORBIDDEN);
        }

        logger.debug("Usernames coinciden!");
        logger.debug("User --> {}", reg.getUser());
        logger.debug("User Login --> {}", reg.getUserLogin());


        try {
            logger.debug("Creando usuario...");
            userService.createUser(reg.getUser());
            logger.debug("Usuario creado!");
            UserLogin userLogin = reg.getUserLogin();
            credentialService.registerPasswordCredentials(userLogin.getUsername(), userLogin.getPassword());
            logger.debug("Registro de credenciales hecho");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserServiceException ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody User user) {
        try {
            profileService.updateUserProfile(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (ProfileServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) throws ProfileServiceException {
        return profileService.getUserProfile(username);
    }

}

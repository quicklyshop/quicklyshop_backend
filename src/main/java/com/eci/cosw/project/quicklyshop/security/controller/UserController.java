package com.eci.cosw.project.quicklyshop.security.controller;

import com.eci.cosw.project.quicklyshop.security.functions.DigestFunction;
import com.eci.cosw.project.quicklyshop.security.model.Token;
import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.model.UserCredential;
import com.eci.cosw.project.quicklyshop.security.model.UserLogin;
import com.eci.cosw.project.quicklyshop.security.service.TokenService;
import com.eci.cosw.project.quicklyshop.security.service.UserCredentialService;
import com.eci.cosw.project.quicklyshop.security.service.UserCredentialServiceException;
import com.eci.cosw.project.quicklyshop.security.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    UserCredentialService credentialService;

    @Autowired
    TokenService tokenService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody UserLogin login) throws ServletException, UserCredentialServiceException {
        logger.debug("Access token requested by: \"{}\"", login.getUsername());

        if (login.getUsername() == null || login.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        String username = login.getUsername();
        String password = login.getPassword();

        User user = userService.getUser(username);

        if (user == null) {
            throw new ServletException("User username not found.");
        }

        UserCredential uCreds = credentialService.getUserCredential(user.getUsername());
        DigestFunction func = credentialService.getDigestFunction(uCreds.getHashFunction());

        if (!func.matches(password, uCreds.getHashedPassword())) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }

        logger.debug("Access token granted to: \"{}\"", login.getUsername());
        return tokenService.generateToken(username, password);
    }

}

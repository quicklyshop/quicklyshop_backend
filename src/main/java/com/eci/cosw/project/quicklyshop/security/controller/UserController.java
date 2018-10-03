package com.eci.cosw.project.quicklyshop.security.controller;

import com.eci.cosw.project.quicklyshop.security.functions.DigestFunction;
import com.eci.cosw.project.quicklyshop.security.model.User;
import com.eci.cosw.project.quicklyshop.security.model.UserCredential;
import com.eci.cosw.project.quicklyshop.security.model.UserLogin;
import com.eci.cosw.project.quicklyshop.security.service.UserCredentialService;
import com.eci.cosw.project.quicklyshop.security.service.UserCredentialServiceException;
import com.eci.cosw.project.quicklyshop.security.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserCredentialService credentialService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Token login(@RequestBody UserLogin login) throws ServletException, UserCredentialServiceException {
        System.out.println("Access token requested by: " + login.getUsername());

        String jwtToken = "";

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

        jwtToken = Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date()).signWith(
                SignatureAlgorithm.HS256, password).compact();

        System.out.println("Access token granted to: " + login.getUsername());
        return new Token(jwtToken);
    }

    public class Token {
        String access_token;

        public Token(String access_token) {
            this.access_token = access_token;
        }

        public String getAccessToken() {
            return access_token;
        }

        public void setAccessToken(String access_token) {
            this.access_token = access_token;
        }
    }

}

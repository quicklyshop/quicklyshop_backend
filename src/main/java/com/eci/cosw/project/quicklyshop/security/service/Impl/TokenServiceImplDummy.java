package com.eci.cosw.project.quicklyshop.security.service.Impl;

import com.eci.cosw.project.quicklyshop.security.model.Token;
import com.eci.cosw.project.quicklyshop.security.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImplDummy implements TokenService {

    private Map<String, Token> tokens;

    public TokenServiceImplDummy() {
        tokens = new HashMap<>();
    }

    @Override
    public Token generateToken(String username, String password) {
        Token newToken = new Token(Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date()).signWith(
                SignatureAlgorithm.HS256, "secretkey").compact());

        tokens.put(username, newToken);

        return newToken;
    }

    @Override
    public boolean validTokenForUser(Token token, String username) {
        return tokens.get(username).getAccessToken().equals(token.getAccessToken());
    }

    @Override
    public boolean validToken(Token token) {
        return tokens.values()
                .parallelStream()
                .anyMatch(e ->
                        e.getAccessToken().equals(token.getAccessToken()));
    }
}

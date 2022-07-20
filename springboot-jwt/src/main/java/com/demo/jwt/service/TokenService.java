package com.demo.jwt.service;

import com.demo.jwt.entity.Token;
import com.demo.jwt.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private static final long EXPIRATION = 60 * 60 * 1000;

    private static final String SECRET = "secret";

    public Token createToken(User user) {
        String token = Jwts.builder()
                .setId(user.getId().toString())
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return new Token(token);
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}

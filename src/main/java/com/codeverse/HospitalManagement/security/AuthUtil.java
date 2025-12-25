package com.codeverse.HospitalManagement.security;


import com.codeverse.HospitalManagement.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtil {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey secretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId" , user.getId().toString())
                .setIssuedAt(new Date(

                ))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(secretKey())
                .compact();
    }

    public String getUserNameFromToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(secretKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();

    }
}

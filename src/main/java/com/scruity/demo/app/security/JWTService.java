package com.scruity.demo.app.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.scruity.demo.app.utility.KeyConstants;

import io.jsonwebtoken.Jwts;

@Service
public class JWTService implements IJWTService {

    @Override
    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();        
        long expirationTime = KeyConstants.JWT_TOKEN_EXPIRATION_TIME * 60 * 1000; // Valid for 10 Minits
        return Jwts.builder()
            .claims()
            .add(claims)
            .subject(username)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expirationTime))
            .and()
            .signWith(getKey()) 
            .compact();     
    }

    private Key getKey()  {
        try{
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGen.generateKey();
        return secretKey;
        }catch(NoSuchAlgorithmException ex){

            ex.printStackTrace();
            throw new RuntimeErrorException(null);
        }
    }
    
}

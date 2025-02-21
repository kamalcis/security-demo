package com.scruity.demo.app.security;

public interface IJWTService {

    String generateToken(String username);
    
}

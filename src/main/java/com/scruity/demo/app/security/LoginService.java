package com.scruity.demo.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.scruity.demo.app.model.User;

@Service
public class LoginService  implements ILoginService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IJWTService jwtService;

    @Override
    public String verify(User user) {
      Authentication authentication =  authenticationManager.authenticate(
                                       new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
      if(authentication.isAuthenticated()) return jwtService.generateToken(user.getUsername());
      return "Fail";
       
                                       
    }
    
}

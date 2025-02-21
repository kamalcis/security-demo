package com.scruity.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.scruity.demo.app.model.User;
import com.scruity.demo.app.service.contract.ILoginService;

@Service
public class LoginService  implements ILoginService{

    @Autowired
    AuthenticationManager authenticationManager;

     @Override
    public String verify(User user) {
      Authentication authentication =  authenticationManager.authenticate(
                                       new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
      if(authentication.isAuthenticated()) return "Success";
      return "Fail";
       
                                       
    }
    
}

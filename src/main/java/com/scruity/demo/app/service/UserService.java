package com.scruity.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.scruity.demo.app.model.User;
import com.scruity.demo.app.repository.contract.IUserRepository;
import com.scruity.demo.app.service.contract.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;
    
   

    @Override
    public User saveUser(User user) {
          return userRepository.save(user);
    }

   
    
}

package com.scruity.demo.app.security;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scruity.demo.app.model.User;
import com.scruity.demo.app.repository.contract.IUserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         
        User user = userRepository.findByUsername(username);
        if(user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("user Not found");
        }
        return null;
            
    };
    
    

}

package com.scruity.demo.app.service.contract;

import org.springframework.stereotype.Service;

import com.scruity.demo.app.model.User;


public interface ILoginService {
   
      String verify(User user);
    
} 

package com.scruity.demo.app.controller;

import org.springframework.web.bind.annotation.RestController;

import com.scruity.demo.app.model.User;
import com.scruity.demo.app.service.contract.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LoginController {
    
    @Autowired
    IUserService userService;

    @PostMapping("/api/user/login")
    public String login(@RequestBody User user) {          
        return userService.verify(user);        
    }
    
    
}

package com.scruity.demo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scruity.demo.app.model.User;
import com.scruity.demo.app.service.contract.IUserService;

@RestController
public class UserController {
    
    @Autowired
    IUserService userService;
    
    @PostMapping("/api/public/user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
       
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);     
        
    }

    

    
}

package com.scruity.demo.app.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class HelloController {
    
    List<String> list = new ArrayList<>();
    
    @GetMapping("/api/public/hello")
    public String hello(HttpServletRequest req){
        System.out.println("Hello Method called");
        try{
         return "Hello everyone ! "+ req.getRequestedSessionId();
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    @GetMapping("/api/admin/hello")
    public String helloAdmin(HttpServletRequest req){
        return "Hello Admin ! " + req.getRequestedSessionId();
    }

    @GetMapping("/api/user/hello")
    public String helloUser(){
        return "Hello User";
    }

    @GetMapping("/api/public/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");
    }

    
    @PostMapping("/api/admin/save")
    public List<String> saveData(@RequestBody String data) {
        
        list.add(data);
        return list;
        
    }
    
   

}

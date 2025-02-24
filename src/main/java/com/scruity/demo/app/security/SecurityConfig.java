package com.scruity.demo.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    JWTFilter jwtFilter;
 
    @Bean
    public SecurityFilterChain seqSecurityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(customizer-> customizer.disable())
               
                .authorizeHttpRequests(request-> request
                .requestMatchers("api/public/**").permitAll()   // public api will never be authenticated.
                .requestMatchers("api/user/login").permitAll() // User login will be authenticated through controller            
                .requestMatchers("api/admin/**").hasRole("ADMIN") 
                .requestMatchers("api/user/**").hasAnyRole("USER", "ADMIN") 
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)            
                .build();
    }

   @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailService) ;
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); 
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}

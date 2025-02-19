package com.scruity.demo.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    CustomUserDetailService customUserDetailService;
 
    @Bean
    public SecurityFilterChain seqSecurityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(customizer-> customizer.disable())
                .authorizeHttpRequests(request-> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                
                .build();

    }

    // @Bean
    // UserDetailsService userDetailsService(){
    //     UserDetails user1 = User.withDefaultPasswordEncoder()
    //                         .username("kiran")
    //                         .password("kiran")
    //                         .roles("USER")
    //                         .build();
    //     UserDetails user2 = User.withDefaultPasswordEncoder()
    //                         .username("adam")
    //                         .password("adam")
    //                         .roles("ADMIN")
    //                         .build();
    //     return new InMemoryUserDetailsManager(user1,user2);
    // }
    
   @Bean
    public AuthenticationProvider daoAuthenticationProvider(){


        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailService) ;
       // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); 

        return provider;
    }
}

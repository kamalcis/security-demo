package com.scruity.demo.app.service.contract;

import com.scruity.demo.app.model.User;

public interface IUserService {    
     
    User saveUser(User user);

    String verify(User user);
    
}

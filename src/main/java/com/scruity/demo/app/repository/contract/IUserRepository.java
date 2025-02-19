package com.scruity.demo.app.repository.contract;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scruity.demo.app.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Long>  {

    Optional<User> findByUsername(String username);
    
}

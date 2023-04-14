package com.chatop.api.services;

import com.chatop.api.models.UserEntity;
import com.chatop.api.repositories.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {

    UserRepository userRepository ;

    public UserService( UserRepository userRepository){
        this.userRepository = userRepository ;
    }

    public UserEntity getUserById(Integer id){
        return userRepository
                .findById(id)
                .orElseThrow();
    }

    public UserEntity getUserByEmail(String email){
        return userRepository
                .findByEmail(email)
                .orElseThrow();
    }

}

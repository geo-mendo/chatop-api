package com.chatop.api.user;

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

}

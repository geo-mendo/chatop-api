package com.chatop.api.user;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService ;

    public UserController(UserService userService){
        this.userService = userService ;
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

}

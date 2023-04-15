package com.chatop.api.services;

import com.chatop.api.models.UserEntity;
import com.chatop.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + mail));

        return new UserEntity(
                user.getId(),
                user.getMail(),
                user.getName(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public UserEntity getUserById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow();
    }
}

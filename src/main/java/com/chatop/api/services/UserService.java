package com.chatop.api.services;

import com.chatop.api.dto.UserResponseDTO;
import com.chatop.api.models.UserEntity;
import com.chatop.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }

    public UserEntity getUserById(Long id) {
        return userRepository
                .findById(Math.toIntExact(id))
                .orElseThrow();
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow();
    }

    public UserEntity createNewUser(UserEntity newUser) {
        newUser.setCreatedAt(LocalDate.now());
        return userRepository
                .save(newUser);
    }

    public UserResponseDTO mapUserEntityToDTO(UserEntity user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}

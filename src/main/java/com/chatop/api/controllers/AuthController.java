package com.chatop.api.controllers;

import com.chatop.api.dto.AuthRequestDTO;
import com.chatop.api.dto.AuthResponseDTO;
import com.chatop.api.dto.RegisterRequest;
import com.chatop.api.dto.UserResponseDTO;
import com.chatop.api.models.UserEntity;
import com.chatop.api.services.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(HttpServletRequest request) throws ServletException, IOException {
        return ResponseEntity.ok(service.getCurrentUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(
            @RequestBody AuthRequestDTO request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}

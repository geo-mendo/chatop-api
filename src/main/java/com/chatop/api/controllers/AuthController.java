package com.chatop.api.controllers;

import com.chatop.api.dto.AuthRequestDTO;
import com.chatop.api.dto.AuthResponseDTO;
import com.chatop.api.dto.RegisterRequest;
import com.chatop.api.dto.UserResponseDTO;
import com.chatop.api.models.UserEntity;
import com.chatop.api.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Récupérer l'utilisateur courant",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne l'utilisateur courant",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Accès non autorisé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(HttpServletRequest request) throws ServletException, IOException {
        return ResponseEntity.ok(service.getCurrentUser(request));
    }

    @Operation(summary = "Enregistrement d'un nouvel utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne les informations d'authentification de l'utilisateur enregistré",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requête - les informations fournies sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @Parameter(description="Requête d'enregistrement contenant les informations de l'utilisateur à enregistrer", required=true)
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @Operation(summary = "Authentifier un utilisateur existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne les informations d'authentification de l'utilisateur authentifié",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requête - les informations fournies sont invalides"),
            @ApiResponse(responseCode = "401", description = "Authentification échouée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(
            @Parameter(description="Requête d'authentification contenant les informations de l'utilisateur à authentifier", required=true)
            @RequestBody AuthRequestDTO request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}

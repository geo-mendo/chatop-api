package com.chatop.api.controllers;

import com.chatop.api.dto.MessageRequestDTO;
import com.chatop.api.dto.MessageResponseDTO;
import com.chatop.api.dto.RentalResponseDTO;
import com.chatop.api.models.MessageEntity;
import com.chatop.api.models.RentalEntity;
import com.chatop.api.models.UserEntity;
import com.chatop.api.services.AuthService;
import com.chatop.api.services.MessageService;
import com.chatop.api.services.RentalService;
import com.chatop.api.services.UserService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping( "/api/messages")
@RequiredArgsConstructor
public class MessageController {


    private final MessageService messageService;

    private final RentalService rentalService;

    private final UserService userService;

    @Operation(summary = "Ajouter un nouveau message",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne le message créé",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requête - les informations fournies sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @PostMapping()
    public ResponseEntity<MessageResponseDTO> addNewMessage(
            @Parameter(description="Requête contenant les informations du message à créer", required=true)
            @RequestBody MessageRequestDTO newMessageDTO) throws IOException {
        UserEntity user =  userService.getUserById(newMessageDTO.getUser_id());
        RentalEntity rental = rentalService.getRentalById(newMessageDTO.getRental_id());
        MessageEntity newMessage = new MessageEntity();
        newMessage.setMessage(newMessageDTO.getMessage());
        newMessage.setUser(user);
        newMessage.setRental(rental);
        newMessage.setCreatedAt(LocalDate.now());
        newMessage.setUpdatedAt(LocalDate.now());
        return ResponseEntity.ok(messageService.createMessage(newMessage));
    }
}

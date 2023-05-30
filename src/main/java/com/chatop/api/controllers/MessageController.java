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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping( "/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private UserService userService;
    @PostMapping()
    public ResponseEntity<MessageResponseDTO> addNewMessage(@RequestBody MessageRequestDTO newMessageDTO) throws IOException {
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

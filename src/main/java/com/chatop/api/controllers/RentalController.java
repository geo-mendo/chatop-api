package com.chatop.api.controllers;

import com.chatop.api.dto.RentalRequestDTO;
import com.chatop.api.dto.RentalResponseDTO;
import com.chatop.api.dto.RentalsResponseDTO;
import com.chatop.api.dto.UserResponseDTO;
import com.chatop.api.models.RentalEntity;
import com.chatop.api.models.UserEntity;
import com.chatop.api.services.AuthService;
import com.chatop.api.services.MultipartFileService;
import com.chatop.api.services.RentalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/rentals",consumes = MediaType.ALL_VALUE)
public class RentalController {

    @Autowired
    private RentalService rentalService;
    @Autowired
    private MultipartFileService multipartFileService;
    @Autowired
    private AuthService authService;


    @GetMapping
    public ResponseEntity<RentalsResponseDTO> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> getOneRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.mapRentalEntityToDTO(rentalService.getRentalById(id)));
    }

    @PostMapping()
    public ResponseEntity<RentalResponseDTO> addNewRental(
            @ModelAttribute("rental") RentalRequestDTO rentalRequestDTO,
            HttpServletRequest request
            ) throws IOException, ServletException {
        if (!rentalRequestDTO.getPicture().isEmpty()) {
            UserResponseDTO currentUser = authService.getCurrentUser(request);
            rentalRequestDTO.setOwner_id(currentUser.getId());
            String fileUrl = multipartFileService.uploadFile(rentalRequestDTO.getPicture());
            return ResponseEntity.ok(rentalService.addNewRental(rentalRequestDTO,fileUrl));
        }
        throw new IOException();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> updateRental(
            @ModelAttribute("rental") RentalRequestDTO rentalRequestDTO,
            @PathVariable Long id,
            HttpServletRequest request
    ) throws IOException, ServletException {
        UserResponseDTO currentUser = authService.getCurrentUser(request);
        rentalRequestDTO.setOwner_id(currentUser.getId());
        return ResponseEntity.ok(rentalService.updateRental(rentalRequestDTO, id));
    }
}

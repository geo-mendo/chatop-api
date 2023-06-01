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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/rentals",consumes = MediaType.ALL_VALUE)
@RequiredArgsConstructor
public class RentalController {


    private final RentalService rentalService;

    private final MultipartFileService multipartFileService;

    private final AuthService authService;


    @Operation(summary = "Récupérer tous les locations",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne la liste de toutes les locations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalsResponseDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @GetMapping
    public ResponseEntity<RentalsResponseDTO> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @Operation(summary = "Récupérer une location spécifique par son ID",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne la location demandée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Location non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> getOneRental(
            @Parameter(description="ID de la location à récupérer", required=true)
            @PathVariable Long id) {
        return ResponseEntity.ok(rentalService.mapRentalEntityToDTO(rentalService.getRentalById(id)));
    }

    @Operation(summary = "Ajouter une nouvelle location",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne la location créée",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requête - les informations fournies sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @PostMapping()
    public ResponseEntity<RentalResponseDTO> addNewRental(
            @Parameter(description="Requête contenant les informations de la location à créer", required=true)
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

    @Operation(summary = "Mettre à jour une location existante",security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retourne la location mise à jour",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requête - les informations fournies sont invalides"),
            @ApiResponse(responseCode = "404", description = "Location non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur") })
    @PutMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> updateRental(
            @Parameter(description="Requête contenant les informations de la location à mettre à jour", required=true)
            @ModelAttribute("rental") RentalRequestDTO rentalRequestDTO,
            @Parameter(description="ID de la location à mettre à jour", required=true)
            @PathVariable Long id,
            HttpServletRequest request
    ) throws IOException, ServletException {
        UserResponseDTO currentUser = authService.getCurrentUser(request);
        rentalRequestDTO.setOwner_id(currentUser.getId());
        return ResponseEntity.ok(rentalService.updateRental(rentalRequestDTO, id));
    }
}

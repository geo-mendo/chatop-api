package com.chatop.api.services;

import com.chatop.api.dto.RentalRequestDTO;
import com.chatop.api.dto.RentalResponseDTO;
import com.chatop.api.dto.RentalsResponseDTO;
import com.chatop.api.exceptions.RentalNotFoundException;
import com.chatop.api.models.RentalEntity;
import com.chatop.api.models.UserEntity;
import com.chatop.api.repositories.RentalRepository;
import com.chatop.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {


    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private  UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public RentalEntity getRentalById(Long id) {
        return rentalRepository
                .findById(id)
                .orElseThrow(() -> new RentalNotFoundException(id));
    }

    public RentalsResponseDTO getAllRentals() {
        RentalsResponseDTO rentalsResponseDTO = new RentalsResponseDTO();
        List<RentalEntity> rentals = Streamable.of(rentalRepository.findAll()).toList();
        rentals.forEach(rentalEntity -> rentalsResponseDTO.getRentals().add(mapRentalEntityToDTO(rentalEntity)) );
        return rentalsResponseDTO;
    }

    public RentalResponseDTO addNewRental(RentalRequestDTO newRental, String fileUrl) {
        RentalEntity newRentalEntity = toEntity(newRental,fileUrl);
        RentalEntity rentalCreated = rentalRepository.save(newRentalEntity);
        return  mapRentalEntityToDTO(rentalCreated);
    }

    public RentalResponseDTO updateRental(RentalRequestDTO rentalUpdate, Long id) {
        UserEntity owner = userRepository.findById(Math.toIntExact(rentalUpdate.getOwner_id()))
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return rentalRepository
                .findById(id)
                .map(rental -> {
                    rental.setName(rentalUpdate.getName());
                    rental.setDescription(rentalUpdate.getDescription());
                    rental.setPrice(rentalUpdate.getPrice());
                    rental.setSurface(rentalUpdate.getSurface());
                    rental.setOwner(owner);
                    rental.setUpdatedAt(LocalDate.now());
                    return mapRentalEntityToDTO(rentalRepository.save(rental));
                })
                .orElseThrow(() -> new RuntimeException("Location non trouvé"));
    }

    public RentalResponseDTO mapRentalEntityToDTO(RentalEntity rentalToMap){
        return new RentalResponseDTO(
                rentalToMap.getId(),
                rentalToMap.getName(),
                rentalToMap.getSurface(),
                rentalToMap.getPrice(),
                rentalToMap.getPicture(),
                rentalToMap.getDescription(),
                rentalToMap.getOwner().getId(),
                rentalToMap.getCreatedAt(),
                rentalToMap.getUpdatedAt()
        );
    }

    public RentalEntity toEntity(RentalRequestDTO dto, String fileUrl) {

        UserEntity owner = userRepository.findById(Math.toIntExact(dto.getOwner_id()))
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return RentalEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .surface(dto.getSurface())
                .price(dto.getPrice())
                .picture(fileUrl)
                .owner(owner)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();
    }

}

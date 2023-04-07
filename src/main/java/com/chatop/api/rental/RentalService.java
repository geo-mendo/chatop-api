package com.chatop.api.rental;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class RentalService {


    private RentalRepository rentalRepository ;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository ;
    }

    public RentalEntity getRentalById(Long id){
        return rentalRepository
                .findById(id)
                .orElseThrow(() -> new RentalNotFoundException(id));
    }

    public Iterable<RentalEntity> getAllRentals(){
        return rentalRepository.findAll() ;
    }

    public RentalEntity addNewRental(RentalEntity newRental){
        return rentalRepository.save(newRental);
    }

    public RentalEntity updateRental(RentalEntity rentalUpdate, Long id){
        return rentalRepository
                    .findById(id)
                    .map( rental -> {
                        rental.setName(rentalUpdate.getName());
                        rental.setDescription(rentalUpdate.getDescription());
                        rental.setPicture(rentalUpdate.getPicture());
                        rental.setSurface(rentalUpdate.getSurface());
                        rental.setOwner_id(rentalUpdate.getOwner_id());
                        return rentalRepository.save(rental);
                    })
                    .orElseGet(()->{
                        rentalUpdate.setId(id);
                        return rentalRepository.save(rentalUpdate);
                    });
    }

}

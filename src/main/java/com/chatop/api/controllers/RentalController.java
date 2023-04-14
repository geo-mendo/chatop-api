package com.chatop.api.controllers;

import com.chatop.api.models.RentalEntity;
import com.chatop.api.services.RentalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService ;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService ;
    }


    @GetMapping("/")
    public Iterable<RentalEntity> getAllRentals(){
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public RentalEntity getOneRental(@PathVariable Long id){
        return rentalService.getRentalById(id);
    }

    @PostMapping("/")
    public RentalEntity addNewRental(@RequestBody RentalEntity newRental ){
        return rentalService.addNewRental(newRental) ;
    }

    @PutMapping("/{id}")
    public RentalEntity updateRental(@RequestBody RentalEntity updateRental ,@PathVariable Long id){
        return  rentalService.updateRental(updateRental,id) ;
    }
}

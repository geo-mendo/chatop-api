package com.chatop.api.controllers;

import com.chatop.api.models.RentalEntity;
import com.chatop.api.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;


    @GetMapping
    public Iterable<RentalEntity> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public RentalEntity getOneRental(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping
    public RentalEntity addNewRental(@RequestBody RentalEntity newRental) {
        return rentalService.addNewRental(newRental);
    }

    @PutMapping("/{id}")
    public RentalEntity updateRental(@RequestBody RentalEntity updateRental, @PathVariable Long id) {
        return rentalService.updateRental(updateRental, id);
    }
}

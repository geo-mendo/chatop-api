package com.chatop.api.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService ;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService ;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
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

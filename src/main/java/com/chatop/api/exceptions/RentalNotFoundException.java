package com.chatop.api.exceptions;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(Long id){
        super("Could not find this rental " + id);
    }
}

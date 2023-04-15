package com.chatop.api.controllers.handler;

import com.chatop.api.exceptions.RentalNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { RentalNotFoundException.class })
    protected ResponseEntity<Object> handleRentalNotFoundException(RentalNotFoundException rentalNotFoundException, WebRequest request) {
        return handleExceptionInternal(rentalNotFoundException, rentalNotFoundException.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = { BadCredentialsException.class })
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException badCredentialsException,
                                                                   WebRequest request) {
        return handleExceptionInternal(badCredentialsException, badCredentialsException.getMessage(),
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = { AccessDeniedException.class })
    protected ResponseEntity<Object> handleForbiddenException(AccessDeniedException accessDeniedException,
                                                              WebRequest request) {
        return handleExceptionInternal(accessDeniedException, accessDeniedException.getMessage(),
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}

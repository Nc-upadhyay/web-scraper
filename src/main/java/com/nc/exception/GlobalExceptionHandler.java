package com.nc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Exception handler for RestaurantIDNotFound exception
    @ExceptionHandler(RestaurantIDNotFound.class)
    public ResponseEntity<?> notFount(RestaurantIDNotFound exception) {
        // Returning the error message along with HTTP status NOT_FOUND
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Exception handler for generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception exception) {
        // Returning the error message along with HTTP status INTERNAL_SERVER_ERROR
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

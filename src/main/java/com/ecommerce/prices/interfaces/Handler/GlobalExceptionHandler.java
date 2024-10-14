package com.ecommerce.prices.interfaces.Handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecommerce.prices.domain.exception.InvalidPriceRequestException;
import com.ecommerce.prices.domain.exception.PriceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePriceNotFoundException(PriceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                "No price found for the given parameters.",
                "PRICE_NOT_FOUND"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPriceRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPriceRequestException(InvalidPriceRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                "The price request contains invalid data: " + ex.getMessage(),
                "INVALID_REQUEST"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                "An unexpected error occurred: " + ex.getMessage(),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.ecommerce.prices.domain.exception;

public class InvalidPriceRequestException extends RuntimeException {
    public InvalidPriceRequestException(String message) {
        super(message);
    }
}

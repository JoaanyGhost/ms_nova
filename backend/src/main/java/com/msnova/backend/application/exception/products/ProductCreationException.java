package com.msnova.backend.application.exception.products;


public class ProductCreationException extends RuntimeException {

    public ProductCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
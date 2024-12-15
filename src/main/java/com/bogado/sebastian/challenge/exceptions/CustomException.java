package com.bogado.sebastian.challenge.exceptions;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {
    private final String message;
    private final HttpStatus statusCode;

    public CustomException(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}

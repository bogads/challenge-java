package com.bogado.sebastian.challenge.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExists extends CustomException {
    public EmailAlreadyExists() {
        super("El correo ya registrado", HttpStatus.BAD_REQUEST);
    }
}

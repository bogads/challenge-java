package com.bogado.sebastian.challenge.controller.rest.request;

import com.bogado.sebastian.challenge.utils.validations.EmailConfigurableRegex;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public class CreateUserRequest {
    @NotNull(message = "es un campo requerido")
    @NotEmpty(message = "no puede ser vacio")
    @Size(min = 1, max = 255, message = "debe tener entre 1 y 255 caracteres")
    private String name;
    @NotNull(message = "es un campo requerido")
    @NotEmpty(message = "no puede ser vacio")
    @Size(min = 1, max = 255, message = "debe tener entre 1 y 255 caracteres")
    @EmailConfigurableRegex
    private String email;
    @NotNull(message = "es un campo requerido")
    @NotEmpty(message = "no puede ser vacio")
    @Size(min = 1, max = 255, message = "debe tener entre 1 y 255 caracteres")
    private String password;
    private List<PhoneRequest> phones;

    public CreateUserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneRequest> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneRequest> phones) {
        this.phones = phones;
    }
}

package com.bogado.sebastian.challenge.controller.rest.request;

import com.bogado.sebastian.challenge.utils.validations.EmailConfigurableRegex;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Solicitud para registrar un nuevo usuario.")
public class CreateUserRequest {
    @Schema(description = "Nombre completo del usuario.", example = "Juan Pérez")
    @NotNull(message = "es un campo requerido")
    @NotEmpty(message = "no puede ser vacio")
    @Size(min = 1, max = 255, message = "debe tener entre 1 y 255 caracteres")
    private String name;
    @NotNull(message = "es un campo requerido")
    @NotEmpty(message = "no puede ser vacio")
    @Size(min = 1, max = 255, message = "debe tener entre 1 y 255 caracteres")
    @EmailConfigurableRegex
    @Schema(description = "Correo electrónico del usuario.", example = "juan.perez@ejemplo.com")
    private String email;
    @NotNull(message = "es un campo requerido")
    @NotEmpty(message = "no puede ser vacio")
    @Size(min = 1, max = 255, message = "debe tener entre 1 y 255 caracteres")
    @Schema(description = "Contraseña del usuario.", example = "password123")
    private String password;
    @Schema(description = "Lista opcional de teléfonos del usuario con sus datos.")
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

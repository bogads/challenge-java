package com.bogado.sebastian.challenge.controller.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Respuesta generada al registrar un usuario.")
public record CreateUserResponse(

        @Schema(description = "Identificador único del usuario.", example = "c12345-a123-4ab5-b123-ab12345cd67e")
        String id,

        @Schema(description = "Fecha y hora de creación del usuario.", example = "2023-05-10T14:48:23")
        LocalDateTime created,

        @Schema(description = "Última fecha y hora en que se modificaron los datos del usuario.", example = "2023-06-10T10:12:45")
        LocalDateTime modified,

        @Schema(description = "Fecha y hora de la última vez que el usuario inició sesión.", example = "2023-06-20T12:30:12")
        @JsonProperty("last_login")
        LocalDateTime lastLogin,

        @Schema(description = "Token de autenticación generado.", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token,

        @Schema(description = "Estado activo/inactivo del usuario.", example = "true")
        @JsonProperty("active")
        Boolean isActive
) {
}
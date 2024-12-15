package com.bogado.sebastian.challenge.controller.rest;

import com.bogado.sebastian.challenge.controller.rest.request.CreateUserRequest;
import com.bogado.sebastian.challenge.controller.rest.response.CreateUserResponse;
import com.bogado.sebastian.challenge.mapper.UserMapper;
import com.bogado.sebastian.challenge.model.entity.UserEntity;
import com.bogado.sebastian.challenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.bogado.sebastian.challenge.utils.ApiConstants.USERS_API;

@RestController
@RequestMapping(USERS_API)
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Registrar usuario",
            description = "Crea un nuevo usuario a partir de los datos proporcionados en el cuerpo de la solicitud.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario registrado exitosamente.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(responseCode = "400", description = "Solicitud inválida. Verifica los datos ingresados."),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
            }
    )
    public CreateUserResponse register(@Valid @RequestBody CreateUserRequest user) {
        UserEntity persistedUser = userService.registerUser(UserMapper.fromCreateUserRequestToEntity(user));
        return UserMapper.fromEntityToCreateUserResponse(persistedUser);
    }
}

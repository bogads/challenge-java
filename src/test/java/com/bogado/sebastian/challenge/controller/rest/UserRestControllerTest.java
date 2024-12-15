package com.bogado.sebastian.challenge.controller.rest;

import com.bogado.sebastian.challenge.controller.rest.request.CreateUserRequest;
import com.bogado.sebastian.challenge.controller.rest.response.CreateUserResponse;
import com.bogado.sebastian.challenge.model.entity.UserEntity;
import com.bogado.sebastian.challenge.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRestControllerTest {

    private final UserService userService = mock(UserService.class);
    private final UserRestController userRestController = new UserRestController(userService);
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
         validator= factory.getValidator();
    }

    @Test
    void testRegisterUser_Success() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("John Doe");
        request.setEmail("john.doe@example.cl");
        request.setPassword("password123");

        UserEntity mockedUserEntity = new UserEntity();
        mockedUserEntity.setId("1234");
        mockedUserEntity.setName(request.getName());
        mockedUserEntity.setEmail(request.getEmail());
        when(userService.registerUser(any(UserEntity.class))).thenReturn(mockedUserEntity);
        CreateUserResponse response = userRestController.register(request);
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(mockedUserEntity.getId());
        assertThat(response.created()).isEqualTo(mockedUserEntity.getCreatedAt());
        assertThat(response.modified()).isEqualTo(mockedUserEntity.getUpdatedAt());
        assertThat(response.lastLogin()).isEqualTo(mockedUserEntity.getLastLogin());
        assertThat(response.isActive()).isEqualTo(mockedUserEntity.getActive());
    }

    @Test
    void testRegisterUser_InvalidRequest_ShouldHaveValidationErrors() {
        CreateUserRequest invalidRequest = new CreateUserRequest();
        invalidRequest.setName("");
        invalidRequest.setEmail("incorrect-email");
        invalidRequest.setPassword("");
        invalidRequest.setPhones(null);

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(invalidRequest);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("name"));
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("email"));
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("password"));
    }

    @Test
    void testRegisterUser_EmailAlreadyExists_ShouldThrowException() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Jane Doe");
        request.setEmail("jane.doe@example.com");
        request.setPassword("securepassword");

        when(userService.registerUser(any(UserEntity.class)))
                .thenThrow(new RuntimeException("Email already exists"));

        assertThatThrownBy(() -> userRestController.register(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Email already exists");
    }

    @Test
    void testRegisterUser_UnexpectedException_ShouldHandleGracefully() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Sarah Connor");
        request.setEmail("sarah.connor@example.com");
        request.setPassword("terminator123");

        when(userService.registerUser(any(UserEntity.class)))
                .thenThrow(new IllegalStateException("Unexpected exception"));
        assertThatThrownBy(() -> userRestController.register(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Unexpected exception");
    }
}
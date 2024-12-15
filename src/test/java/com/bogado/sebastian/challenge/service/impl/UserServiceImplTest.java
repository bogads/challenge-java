package com.bogado.sebastian.challenge.service.impl;

import com.bogado.sebastian.challenge.exceptions.EmailAlreadyExists;
import com.bogado.sebastian.challenge.model.entity.UserEntity;
import com.bogado.sebastian.challenge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, "testaplicationchallengejwtsecretneedtobeabiggerkey");
    }

    @Test
    void registerUser_WhenEmailExists_ShouldThrowException() {
        String existingEmail = "test@example.com";
        UserEntity user = new UserEntity();
        user.setEmail(existingEmail);
        when(userRepository.findByEmail(existingEmail)).thenReturn(Optional.of(new UserEntity()));
        assertThrows(EmailAlreadyExists.class, () -> userService.registerUser(user));
        verify(userRepository, times(1)).findByEmail(existingEmail);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void registerUser_WhenEmailIsNew_ShouldRegisterUserSuccessfully() {
        String email = "newuser@example.com";
        String password = "password123";
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserEntity result = userService.registerUser(user);
        
        assertNotNull(result.getToken());
        assertTrue(result.getActive());
        assertNotNull(result.getLastLogin());
        assertEquals(email, result.getEmail());

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void registerUser_ShouldRegisterSuccessfully() {
        UserEntity user = new UserEntity();
        user.setEmail("test@example.com");
        user.setPassword("password123");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserEntity result = userService.registerUser(user);

        assertNotNull(result.getToken());
        assertTrue(result.getActive());
        assertNotNull(result.getLastLogin());
        assertNotEquals("password123", result.getPassword());
        verify(userRepository, times(1)).save(user);
    }
}
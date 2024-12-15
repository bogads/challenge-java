package com.bogado.sebastian.challenge.controller.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record CreateUserResponse(String id, LocalDateTime created,
                                 LocalDateTime modified,
                                 @JsonProperty("last_login") LocalDateTime lastLogin,
                                 String token,
                                 Boolean isActive) {
}

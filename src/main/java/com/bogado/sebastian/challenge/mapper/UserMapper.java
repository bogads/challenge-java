package com.bogado.sebastian.challenge.mapper;

import com.bogado.sebastian.challenge.controller.rest.request.CreateUserRequest;
import com.bogado.sebastian.challenge.controller.rest.response.CreateUserResponse;
import com.bogado.sebastian.challenge.model.entity.UserEntity;

public final class UserMapper {
    private UserMapper() {
    }

    public static UserEntity fromCreateUserRequestToEntity(CreateUserRequest request) {
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhones(PhoneMapper.fromPhoneRequestListToEntityList(request.getPhones()));
        return user;
    }

    public static CreateUserResponse fromEntityToCreateUserResponse(UserEntity persistedUser) {
        return new CreateUserResponse(persistedUser.getId(), persistedUser.getCreatedAt(), persistedUser.getUpdatedAt(), persistedUser.getLastLogin(), persistedUser.getToken(), persistedUser.getActive());
    }
}

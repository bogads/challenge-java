package com.bogado.sebastian.challenge.repository;

import com.bogado.sebastian.challenge.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends BaseEntityRepository<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}

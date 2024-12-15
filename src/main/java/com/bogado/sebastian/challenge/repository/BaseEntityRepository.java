package com.bogado.sebastian.challenge.repository;

import com.bogado.sebastian.challenge.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T, String> {

}

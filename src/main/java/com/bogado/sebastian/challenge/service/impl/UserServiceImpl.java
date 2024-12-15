package com.bogado.sebastian.challenge.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.bogado.sebastian.challenge.exceptions.EmailAlreadyExists;
import com.bogado.sebastian.challenge.model.entity.UserEntity;
import com.bogado.sebastian.challenge.repository.UserRepository;
import com.bogado.sebastian.challenge.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService {
    @Value("${jwt.secret}")
    private String secretKey;
    private final UserRepository userRepository;
    private final BCrypt.Hasher BCrypt;

    public UserServiceImpl(UserRepository userRepository, at.favre.lib.crypto.bcrypt.BCrypt.Hasher bCrypt) {
        this.userRepository = userRepository;
        BCrypt = bCrypt;
    }

    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public UserEntity registerUser(UserEntity user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExists();
        }
        String token = Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .expiration(java.sql.Timestamp.valueOf(LocalDateTime.now().plusHours(1)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        user.setToken(token);
        user.setPassword(new String(BCrypt.hash(10,user.getPassword().toCharArray())));
        user.setActive(true);
        user.setLastLogin(LocalDateTime.now());
        return userRepository.save(user);
    }
}

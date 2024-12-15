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

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService {

    private final String secretKey;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, @Value("${jwt.secret}") String secretKey) {
        this.userRepository = userRepository;
        this.secretKey = secretKey;
    }

    @Transactional(rollbackFor = Exception.class, timeout = 10)
    public UserEntity registerUser(UserEntity user) {
        var existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExists();
        }
        String hashedPassword = BCrypt.withDefaults()
                .hashToString(10, user.getPassword().toCharArray()); //
        user.setPassword(hashedPassword);
        var token = Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .expiration(Timestamp.valueOf(LocalDateTime.now().plusHours(1)))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
        user.setToken(token);
        user.setActive(true);
        user.setLastLogin(LocalDateTime.now());
        return userRepository.save(user);
    }
}

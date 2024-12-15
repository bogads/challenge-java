package com.bogado.sebastian.challenge.configuration;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BCryptConfiguration {
    @Bean
    public BCrypt.Hasher bCryptHasher() {
        return BCrypt.withDefaults();
    }
}

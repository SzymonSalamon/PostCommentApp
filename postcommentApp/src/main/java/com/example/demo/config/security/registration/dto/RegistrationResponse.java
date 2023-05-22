package com.example.demo.config.security.registration.dto;

import com.example.demo.models.CustomUserEntity;

public record RegistrationResponse(Long id, String username) {
    public static RegistrationResponse ofUserEntity(CustomUserEntity userEntity) {
        return new RegistrationResponse(userEntity.getId(), userEntity.getUsername());
    }
}

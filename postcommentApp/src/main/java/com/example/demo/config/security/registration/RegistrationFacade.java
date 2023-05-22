package com.example.demo.config.security.registration;

import com.example.demo.config.security.registration.dto.RegistrationRequest;
import com.example.demo.config.security.registration.dto.RegistrationResponse;
import com.example.demo.models.CustomUserEntity;

import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public RegistrationResponse registerNewUser(final RegistrationRequest registrationRequest) {

        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            throw new IllegalStateException();
        }
        CustomUserEntity user = new CustomUserEntity();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        CustomUserEntity savedUser = userRepository.save(user);

        return RegistrationResponse.ofUserEntity(savedUser);

    }

}

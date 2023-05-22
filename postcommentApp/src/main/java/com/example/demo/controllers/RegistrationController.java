package com.example.demo.controllers;

import com.example.demo.config.security.registration.RegistrationFacade;
import com.example.demo.config.security.registration.dto.RegistrationRequest;
import com.example.demo.config.security.registration.dto.RegistrationResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class RegistrationController {
    private final RegistrationFacade registrationFacade;

    @ApiOperation("Register to the App")
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser (
            @RequestBody @Valid final RegistrationRequest registrationRequest) {

        RegistrationResponse response = registrationFacade.registerNewUser(registrationRequest);
        return ResponseEntity.ok(response);
    }
}

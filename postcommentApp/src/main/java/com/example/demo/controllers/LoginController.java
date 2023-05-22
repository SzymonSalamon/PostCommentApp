package com.example.demo.controllers;

import com.example.demo.config.security.auth.AuthenticationRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @ApiOperation("Log in")
    @PostMapping("/login")
    public void login (@RequestBody AuthenticationRequest credentials) {}
}

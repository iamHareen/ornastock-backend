// src/main/java/com/musketeers/jewelverse/controller/auth/AuthController.java
// The REST controller for public authentication actions.

package com.musketeers.jewelverse.controller.auth;

import com.musketeers.jewelverse.dto.auth.LoginResponse;
import com.musketeers.jewelverse.dto.auth.LoginRequest;
import com.musketeers.jewelverse.dto.auth.RegisterRequest;
import com.musketeers.jewelverse.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody RegisterRequest registerRequest) {
        authService.registerCustomer(registerRequest);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
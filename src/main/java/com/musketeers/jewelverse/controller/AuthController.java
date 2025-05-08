package com.musketeers.jewelverse.controller;

import com.musketeers.jewelverse.dto.request.LoginRequest;
import com.musketeers.jewelverse.dto.request.RegisterRequest;
import com.musketeers.jewelverse.dto.response.AuthResponse;
import com.musketeers.jewelverse.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}


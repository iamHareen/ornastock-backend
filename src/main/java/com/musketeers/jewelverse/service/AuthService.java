package com.musketeers.jewelverse.service;

import com.musketeers.jewelverse.dto.request.LoginRequest;
import com.musketeers.jewelverse.dto.request.RegisterRequest;
import com.musketeers.jewelverse.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}

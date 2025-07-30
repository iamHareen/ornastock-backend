// src/main/java/com/musketeers/jewelverse/service/auth/AuthService.java
// The service interface for authentication logic.

package com.musketeers.jewelverse.service.auth;

import com.musketeers.jewelverse.dto.auth.LoginResponse;
import com.musketeers.jewelverse.dto.auth.LoginRequest;
import com.musketeers.jewelverse.dto.auth.RegisterRequest;

public interface AuthService {
    void registerCustomer(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
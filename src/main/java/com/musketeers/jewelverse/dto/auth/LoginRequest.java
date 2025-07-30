// src/main/java/com/musketeers/jewelverse/dto/auth/LoginRequest.java
// DTO for a user to log in.

package com.musketeers.jewelverse.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
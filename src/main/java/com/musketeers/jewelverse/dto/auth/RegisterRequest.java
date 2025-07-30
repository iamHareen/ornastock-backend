// src/main/java/com/musketeers/jewelverse/dto/auth/RegisterRequest.java
// DTO for a new customer to register.

package com.musketeers.jewelverse.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
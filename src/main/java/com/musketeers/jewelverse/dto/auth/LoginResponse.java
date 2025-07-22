package com.musketeers.jewelverse.dto.auth;

import com.musketeers.jewelverse.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UserRole role;
    private String firstName;
    private String lastName;
    private String email;
}
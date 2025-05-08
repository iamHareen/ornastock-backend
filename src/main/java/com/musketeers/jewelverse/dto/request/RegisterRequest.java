package com.musketeers.jewelverse.dto.request;

import com.musketeers.jewelverse.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
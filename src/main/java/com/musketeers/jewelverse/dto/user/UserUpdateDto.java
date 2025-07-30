// src/main/java/com/musketeers/jewelverse/dto/user/UserUpdateDto.java
// DTO for the Admin to update a user's details.

package com.musketeers.jewelverse.dto.user;

import com.musketeers.jewelverse.model.enums.UserRole;
import lombok.Data;

@Data
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private UserRole role;
    private Boolean enabled; // To enable or disable a user account
}
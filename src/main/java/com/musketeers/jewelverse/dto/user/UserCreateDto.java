// src/main/java/com/musketeers/jewelverse/dto/user/UserCreateDto.java
// DTO for the Admin to create a new user (Manager or Sales Assistant).

package com.musketeers.jewelverse.dto.user;

import com.musketeers.jewelverse.model.enums.UserRole;
import lombok.Data;

@Data
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password; // Admin sets the initial password
    private UserRole role; // e.g., MANAGER, SALES_ASSISTANT
}
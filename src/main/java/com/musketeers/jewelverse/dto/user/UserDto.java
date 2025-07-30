// src/main/java/com/musketeers/jewelverse/dto/user/UserDto.java
// DTO for displaying user information to the Admin.

package com.musketeers.jewelverse.dto.user;

import com.musketeers.jewelverse.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private boolean enabled;
    private LocalDateTime createdAt;
}

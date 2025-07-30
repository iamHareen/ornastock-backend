// src/main/java/com/musketeers/jewelverse/service/user/UserService.java
// The service interface for user management logic.

package com.musketeers.jewelverse.service.user;

import com.musketeers.jewelverse.dto.user.UserCreateDto;
import com.musketeers.jewelverse.dto.user.UserDto;
import com.musketeers.jewelverse.dto.user.UserUpdateDto;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto createUser(UserCreateDto createDto);
    UserDto updateUser(Long id, UserUpdateDto updateDto);
    void deleteUser(Long id);
}
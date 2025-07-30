// src/main/java/com/musketeers/jewelverse/controller/admin/AdminController.java
// The REST controller for Admin-only actions.

package com.musketeers.jewelverse.controller.admin;

import com.musketeers.jewelverse.dto.user.UserCreateDto;
import com.musketeers.jewelverse.dto.user.UserDto;
import com.musketeers.jewelverse.dto.user.UserUpdateDto;
import com.musketeers.jewelverse.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users") // Endpoint for managing users
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    // NOTE: When security is added, all methods in this controller would be secured
    // with @PreAuthorize("hasRole('ADMIN')")

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateDto createDto) {
        UserDto newUser = userService.createUser(createDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto updateDto) {
        return ResponseEntity.ok(userService.updateUser(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
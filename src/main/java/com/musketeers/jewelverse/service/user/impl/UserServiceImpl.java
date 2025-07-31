// src/main/java/com/musketeers/jewelverse/service/user/impl/UserServiceImpl.java
// The implementation of the UserService.

package com.musketeers.jewelverse.service.user.impl;

import com.musketeers.jewelverse.dto.user.UserCreateDto;
import com.musketeers.jewelverse.dto.user.UserDto;
import com.musketeers.jewelverse.dto.user.UserUpdateDto;
import com.musketeers.jewelverse.exception.ResourceNotFoundException;
import com.musketeers.jewelverse.exception.UserAlreadyExistsException;
import com.musketeers.jewelverse.util.mapper.UserMapper;
import com.musketeers.jewelverse.model.entity.user.Role;
import com.musketeers.jewelverse.model.entity.user.User;
import com.musketeers.jewelverse.repository.RoleRepository;
import com.musketeers.jewelverse.repository.UserRepository;
import com.musketeers.jewelverse.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder; // From your SecurityConfig

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        // Exclude customers from this list for admin management
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() != null && !user.getRole().getName().equals("CUSTOMER"))
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public UserDto createUser(UserCreateDto createDto) {
        // Check if user with the same email already exists
        if (userRepository.existsByEmail(createDto.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + createDto.getEmail() + " already exists.");
        }

        User newUser = userMapper.toUserEntity(createDto);
        // Encode the password before saving
        newUser.setPassword(passwordEncoder.encode(createDto.getPassword()));

        // Find and assign the role
        Role role = roleRepository.findByName(createDto.getRole().name())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + createDto.getRole().name()));
        newUser.setRole(role);

        User savedUser = userRepository.save(newUser);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserUpdateDto updateDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setFirstName(updateDto.getFirstName());
        existingUser.setLastName(updateDto.getLastName());
        existingUser.setEnabled(updateDto.getEnabled());

        // Update role if it's provided
        if (updateDto.getRole() != null) {
            Role role = roleRepository.findByName(updateDto.getRole().name())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + updateDto.getRole().name()));
            existingUser.setRole(role);
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toUserDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
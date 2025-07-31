// src/main/java/com/musketeers/jewelverse/service/auth/impl/AuthServiceImpl.java
// The implementation of the AuthService.

package com.musketeers.jewelverse.service.auth.impl;

import com.musketeers.jewelverse.dto.auth.LoginResponse;
import com.musketeers.jewelverse.dto.auth.LoginRequest;
import com.musketeers.jewelverse.dto.auth.RegisterRequest;
import com.musketeers.jewelverse.exception.UserAlreadyExistsException;
import com.musketeers.jewelverse.util.mapper.AuthMapper;
import com.musketeers.jewelverse.model.entity.user.User;
import com.musketeers.jewelverse.repository.UserRepository;
import com.musketeers.jewelverse.security.jwt.JwtTokenProvider; // We need this to generate a token
import com.musketeers.jewelverse.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager; // From your SecurityConfig
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public void registerCustomer(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("An account with this email already exists.");
        }

        User newUser = authMapper.registerRequestToUser(registerRequest);
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        // Role is already set in the mapper as CUSTOMER

        userRepository.save(newUser);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // This block uses Spring Security to authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Once authenticated, generate a JWT token
        String jwt = jwtTokenProvider.generateAccessToken(authentication);

        // Get user details to return in the response
        User user = userRepository.findByEmail(loginRequest.getEmail()).get();

        return LoginResponse.builder()
                .token(jwt)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getUserRole())
                .build();
    }
}
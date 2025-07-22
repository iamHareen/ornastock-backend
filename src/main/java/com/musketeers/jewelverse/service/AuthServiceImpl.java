package com.musketeers.jewelverse.service;

import com.musketeers.jewelverse.dto.request.LoginRequestDto;
import com.musketeers.jewelverse.dto.request.RegisterRequestDto;
import com.musketeers.jewelverse.dto.response.AuthResponseDto;
import com.musketeers.jewelverse.entity.user.User;
import com.musketeers.jewelverse.repository.UserRepository;
import com.musketeers.jewelverse.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String registerCustomer(RegisterRequestDto request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return "Customer registered successfully";
    }

    @Override
    public String registerSalesperson(RegisterRequestDto request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return "Salesperson registered successfully";
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());
        String refreshedToken = jwtTokenProvider.refreshToken(token);

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(token);
        authResponseDto.setUserId(user.getId().toString());
        authResponseDto.setRefreshToken(refreshedToken);
        
//        authResponseDto.setRole("CUSTOMER");

        return authResponseDto;
    }

}


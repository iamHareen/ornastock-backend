package com.musketeers.jewelverse.service;

import com.musketeers.jewelverse.dto.request.LoginRequestDto;
import com.musketeers.jewelverse.dto.request.RegisterRequestDto;
import com.musketeers.jewelverse.dto.response.AuthResponseDto;

public interface AuthService {
    String registerCustomer(RegisterRequestDto request);
    String registerSalesperson(RegisterRequestDto request);
    AuthResponseDto login(LoginRequestDto request);
}

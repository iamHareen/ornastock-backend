package com.musketeers.jewelverse.dto.response;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;
    private String refreshToken;
    private String userId;
    private String role;
}

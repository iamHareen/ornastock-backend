package com.musketeers.jewelverse.dto.response;

import lombok.Data;

@Data
public class APIResponseDto {
    private String message;
    private boolean success;
    private Object data;
}

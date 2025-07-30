// src/main/java/com/musketeers/jewelverse/exception/AuthenticationException.java
// NEW FILE: Exception for handling bad credentials during login.

package com.musketeers.jewelverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for handling authentication failures, such as bad credentials.
 * Responds with a 401 UNAUTHORIZED status code.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}
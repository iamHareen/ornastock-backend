// src/main/java/com/musketeers/jewelverse/exception/UnauthorizedException.java
// Exception for authorization failures (access denied).

package com.musketeers.jewelverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for authorization failures (e.g., user does not have the correct role).
 * Responds with a 403 FORBIDDEN status code.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
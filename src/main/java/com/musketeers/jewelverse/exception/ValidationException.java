// src/main/java/com/musketeers/jewelverse/exception/ValidationException.java
// NEW FILE: Exception for handling business logic validation failures.

package com.musketeers.jewelverse.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Map;

/**
 * Custom exception for handling business logic validation failures.
 * Responds with a 400 BAD REQUEST status code and can hold a map of errors.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class ValidationException extends RuntimeException {

    private final Map<String, String> errors;

    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
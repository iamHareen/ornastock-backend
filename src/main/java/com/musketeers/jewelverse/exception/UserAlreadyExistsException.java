// src/main/java/com/musketeers/jewelverse/exception/UserAlreadyExistsException.java

package com.musketeers.jewelverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for registration attempts with an email that is already in use.
 * Responds with a 409 CONFLICT status code.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
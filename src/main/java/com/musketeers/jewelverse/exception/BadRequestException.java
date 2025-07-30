// src/main/java/com/musketeers/jewelverse/exception/BadRequestException.java
// A general-purpose exception for invalid requests.

package com.musketeers.jewelverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A generic exception for various bad requests from the client.
 * Responds with a 400 BAD REQUEST status code.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
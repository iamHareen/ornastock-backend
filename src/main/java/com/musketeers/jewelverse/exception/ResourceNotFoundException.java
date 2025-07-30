// src/main/java/com/musketeers/jewelverse/exception/ResourceNotFoundException.java

package com.musketeers.jewelverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for cases where a requested resource is not found in the database.
 * The @ResponseStatus annotation tells Spring to respond with a 404 NOT FOUND status code.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
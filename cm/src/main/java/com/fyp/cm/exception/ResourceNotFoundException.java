package com.fyp.cm.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

// A custom exception class for resource not found
@ResponseStatus(HttpStatus.NOT_FOUND) // return 404 NOT FOUND status code
public class ResourceNotFoundException extends RuntimeException {

    // A constructor that takes a message as an argument
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // You can also add other constructors or methods as needed
}


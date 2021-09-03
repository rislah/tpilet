package com.rislah.tpilet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class PathNotFoundException extends RuntimeException {
    public PathNotFoundException(String message) {
        super(message);
    }
}

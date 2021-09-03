package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.RecordAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)

public class RouteExistsException extends RecordAlreadyExistsException {
    public RouteExistsException() {
        super("Route already exists");
    }
}

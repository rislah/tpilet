package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.RecordAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)

public class LocationExistsException extends RecordAlreadyExistsException {
    public LocationExistsException(String location) {
        super(String.format("Location '%s' already exists", location));
    }

    public LocationExistsException() {
        super("Location already exists");
    }
}

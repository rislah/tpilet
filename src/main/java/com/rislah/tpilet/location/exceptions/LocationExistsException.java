package com.rislah.tpilet.location.exceptions;

import com.rislah.tpilet.error.RecordAlreadyExistsException;

public class LocationExistsException extends RecordAlreadyExistsException {
    public LocationExistsException(String location) {
        super(String.format("Location '%s' already exists", location));
    }

    public LocationExistsException() {
        super("Location already exists");
    }
}
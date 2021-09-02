package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.RecordAlreadyExistsException;

public class RouteExistsException extends RecordAlreadyExistsException {
    public RouteExistsException() {
        super("Route already exists");
    }
}

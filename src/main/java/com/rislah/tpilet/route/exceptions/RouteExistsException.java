package com.rislah.tpilet.route.exceptions;

import com.rislah.tpilet.error.RecordAlreadyExistsException;

public class RouteExistsException extends RecordAlreadyExistsException {
    public RouteExistsException() {
        super("Route already exists");
    }
}

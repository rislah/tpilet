package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.NotFoundException;

public class RouteNotFoundException extends NotFoundException {
    public RouteNotFoundException() {
        super("Route not found");
    }
}

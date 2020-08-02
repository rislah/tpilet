package com.rislah.tpilet.route.exceptions;

import com.rislah.tpilet.error.NotFoundException;

public class RouteNotFoundException extends NotFoundException {
    public RouteNotFoundException() {
        super("Route not found");
    }
}

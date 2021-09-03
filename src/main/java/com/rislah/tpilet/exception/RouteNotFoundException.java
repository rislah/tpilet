package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class RouteNotFoundException extends NotFoundException {
    public RouteNotFoundException() {
        super("Route not found");
    }
}

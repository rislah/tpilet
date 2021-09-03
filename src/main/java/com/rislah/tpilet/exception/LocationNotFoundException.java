package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class LocationNotFoundException extends NotFoundException {
    public LocationNotFoundException(String location) {
        super(String.format("Location '%s' not found", location));
    }

    public LocationNotFoundException() {
        super("Location not foound");
    }
}

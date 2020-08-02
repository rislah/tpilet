package com.rislah.tpilet.location.exceptions;

import com.rislah.tpilet.error.NotFoundException;

public class LocationNotFoundException extends NotFoundException {
    public LocationNotFoundException(String location) {
        super(String.format("Location '%s' not found", location));
    }

    public LocationNotFoundException() {
        super("Location not foound");
    }
}

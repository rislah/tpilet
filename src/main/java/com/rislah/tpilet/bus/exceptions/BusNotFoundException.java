package com.rislah.tpilet.bus.exceptions;

import com.rislah.tpilet.error.NotFoundException;

public class BusNotFoundException extends NotFoundException {
    public BusNotFoundException() {
        super("Bus not found");
    }
}

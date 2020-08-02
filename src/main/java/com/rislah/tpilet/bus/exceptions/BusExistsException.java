package com.rislah.tpilet.bus.exceptions;

import com.rislah.tpilet.error.NotFoundException;

public class BusExistsException  extends NotFoundException {
    public BusExistsException() {
        super("Bus already exists");
    }
}

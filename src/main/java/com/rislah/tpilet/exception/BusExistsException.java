package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.NotFoundException;

public class BusExistsException  extends NotFoundException {
    public BusExistsException() {
        super("Bus already exists");
    }
}

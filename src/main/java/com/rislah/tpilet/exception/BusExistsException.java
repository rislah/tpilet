package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusExistsException  extends NotFoundException {
    public BusExistsException() {
        super("Bus already exists");
    }
}

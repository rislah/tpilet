package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.RecordAlreadyExistsException;

public class UserExistsException extends RecordAlreadyExistsException {
    public UserExistsException() {
        super("Route already exists");
    }
}

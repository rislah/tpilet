package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.RecordAlreadyExistsException;

public class CompanyExistsException extends RecordAlreadyExistsException {
    public CompanyExistsException() {
        super("Company already exists");
    }
}

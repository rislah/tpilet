package com.rislah.tpilet.company.exceptions;

import com.rislah.tpilet.error.RecordAlreadyExistsException;

public class CompanyExistsException extends RecordAlreadyExistsException {
    public CompanyExistsException() {
        super("Company already exists");
    }
}

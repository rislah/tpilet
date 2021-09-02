package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.NotFoundException;

public class CompanyNotFoundException extends NotFoundException {
    public CompanyNotFoundException() {
        super("Company not found");
    }
}

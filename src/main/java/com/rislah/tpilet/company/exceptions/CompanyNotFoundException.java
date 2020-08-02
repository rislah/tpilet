package com.rislah.tpilet.company.exceptions;

import com.rislah.tpilet.error.NotFoundException;

public class CompanyNotFoundException extends NotFoundException {
    public CompanyNotFoundException() {
        super("Company not found");
    }
}

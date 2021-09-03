package com.rislah.tpilet.exception;

import com.rislah.tpilet.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class CompanyNotFoundException extends NotFoundException {
    public CompanyNotFoundException() {
        super("Company not found");
    }
}

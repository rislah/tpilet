package com.rislah.tpilet.controller;

import com.rislah.tpilet.error.NotFoundException;
import com.rislah.tpilet.exception.PathNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping(path = "/error", produces = "application/json")
    public void handleError(HttpServletRequest request) {
        throw new NotFoundException("Path not found");
    }
}

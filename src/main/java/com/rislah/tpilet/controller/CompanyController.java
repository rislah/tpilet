package com.rislah.tpilet.controller;

import com.rislah.tpilet.service.CompanyService;
import com.rislah.tpilet.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {
    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping(path = "/companies", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto create(@RequestBody @Valid CompanyDto companyDto) {
        return service.create(companyDto);
    }

    @GetMapping(path = "/companies")
    public List<CompanyDto> findAll() {
        return service.findAll();
    }
}

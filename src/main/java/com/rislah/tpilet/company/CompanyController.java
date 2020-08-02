package com.rislah.tpilet.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @PostMapping(path = "/companies", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody @Valid CompanyDto companyDto) {
        companyService.addCompany(companyMapper.companyDtotoCompany(companyDto));
    }

    @GetMapping(path = "/companies")
    public List<CompanyDto> getAllCompanies() {
        return companyMapper.companiesToCompanyDtos(companyService.findAllCompanies());
    }
}

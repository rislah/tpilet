package com.rislah.tpilet.service;

import com.rislah.tpilet.dto.CompanyDto;
import com.rislah.tpilet.exception.CompanyExistsException;
import com.rislah.tpilet.exception.CompanyNotFoundException;
import com.rislah.tpilet.mapper.CompanyMapper;
import com.rislah.tpilet.model.Company;
import com.rislah.tpilet.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    @Autowired
    public CompanyService(CompanyRepository repository, CompanyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CompanyDto create(CompanyDto dto) {
        if (repository.existsCompanyByName(dto.getName())) {
            throw new CompanyExistsException();
        }
        Company company = mapper.companyDtotoCompany(dto);
        repository.saveAndFlush(company);
        return dto;
    }

    public List<CompanyDto> findAll() {
        List<CompanyDto> companies = repository.findAll()
                .stream()
                .map(mapper::companyToCompanyDto)
                .collect(Collectors.toList());
        if (companies.size() == 0) {
            throw new CompanyNotFoundException();
        }
        return companies;
    }

    public List<CompanyDto> findAllCompaniesAndBuses() {
        return repository.findAllWithBuses().stream().map(mapper::companyToCompanyDto).toList();
    }
}

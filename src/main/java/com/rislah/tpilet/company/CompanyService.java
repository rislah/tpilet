package com.rislah.tpilet.company;

import com.rislah.tpilet.company.exceptions.CompanyExistsException;
import com.rislah.tpilet.company.exceptions.CompanyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void addCompany(Company company) {
        if (companyRepository.existsCompanyByName(company.getName())) {
            throw new CompanyExistsException();
        }
        companyRepository.save(company);
    }

    public List<Company> findAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        if (companies.size() == 0) {
            throw new CompanyNotFoundException();
        }
        return companies;
    }

//    public List<Company> findAllCompaniesAndBuses() {
//        return companyRepository.findAllWithBuses();
//    }
}

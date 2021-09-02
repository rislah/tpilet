package com.rislah.tpilet.company;

import com.rislah.tpilet.dto.CompanyDto;
import com.rislah.tpilet.exception.CompanyExistsException;
import com.rislah.tpilet.exception.CompanyNotFoundException;
import com.rislah.tpilet.model.Company;
import com.rislah.tpilet.repository.CompanyRepository;
import com.rislah.tpilet.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CompanyServiceTest {
    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Test
    void testAddCompanyIfCompanyAlreadyExists() {
        CompanyDto company = getCompanyDto();
        when(companyRepository.existsCompanyByName(company.getName())).thenReturn(true);
        assertThrows(CompanyExistsException.class, () -> {
            companyService.create(company);
        });
    }

    @Test
    void testFindAllCompaniesIfNoCompaniesFound() {
        when(companyRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(CompanyNotFoundException.class, () -> {
            companyService.findAll();
        });
    }

    private CompanyDto getCompanyDto() {
        return CompanyDto.builder()
                .email("email@gmail.com")
                .name("test ou")
                .phone(5125125)
                .build();
    }
}

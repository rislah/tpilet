package com.rislah.tpilet.company;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	Company companyDtotoCompany(CompanyDto dto);

	List<CompanyDto> companiesToCompanyDtos(List<Company> entities);

	CompanyDto companyToCompanyDto(Company entity);
}
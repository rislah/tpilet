package com.rislah.tpilet.mapper;

import java.util.List;

import com.rislah.tpilet.dto.CompanyDto;
import com.rislah.tpilet.model.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	Company companyDtotoCompany(CompanyDto dto);

	List<CompanyDto> companiesToCompanyDtos(List<Company> entities);

	CompanyDto companyToCompanyDto(Company entity);
}
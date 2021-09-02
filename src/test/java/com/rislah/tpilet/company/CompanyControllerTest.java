package com.rislah.tpilet.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rislah.tpilet.controller.CompanyController;
import com.rislah.tpilet.dto.CompanyDto;
import com.rislah.tpilet.mapper.CompanyMapper;
import com.rislah.tpilet.model.Company;
import com.rislah.tpilet.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.rislah.tpilet.ResponseBodyMatchers.responseBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CompanyController.class, CompanyMapper.class})
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @MockBean
    private CompanyService companyService;

    @Test
    void testAddCompanyIfMapsToBusinessModel() throws Exception {
        CompanyDto companyDto = getCompanyDto();
        mockMvc.perform(post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDto))).andExpect(status().isCreated());

        ArgumentCaptor<CompanyDto> companyCaptor = ArgumentCaptor.forClass(CompanyDto.class);
        verify(companyService, times(1)).create(companyCaptor.capture());

        assertThat(companyCaptor.getValue().getEmail()).isEqualTo(companyDto.getEmail());
        assertThat(companyCaptor.getValue().getName()).isEqualTo(companyDto.getName());
        assertThat(companyCaptor.getValue().getPhone()).isEqualTo(companyDto.getPhone());
    }

    @Test
    void testAddCompanyIfSuccess() throws Exception {
        CompanyDto companyDto = getCompanyDto();
        mockMvc.perform(post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDto))).andExpect(status().isCreated());
    }

    @Test
    void testAddCompanyIfNameBlank() throws Exception {
        CompanyDto companyDto = getCompanyDto();
        companyDto.setName("");

        mockMvc.perform(post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("name", "must not be blank"));
    }

    @Test
    void testAddCompanyIfNameNull() throws Exception {
        CompanyDto companyDto = getCompanyDto();
        companyDto.setName(null);

        mockMvc.perform(post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("name", "must not be blank"));
    }

    @Test
    void testAddCompanyIfNameLessThanMin() throws Exception {
        CompanyDto companyDto = getCompanyDto();
        companyDto.setName("asd");

        mockMvc.perform(post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDto))
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("name", "length must be between 4 and 100"));
    }

    @Test
    void testAddCompanyIfInvalidEmail() throws Exception {
        CompanyDto companyDto = getCompanyDto();
        companyDto.setEmail("asdasdasd");

        mockMvc.perform(post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDto))
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("email", "must be a well-formed email address"));
    }

    @Test
    void testAddCompanyIfEmailNull() throws Exception {
        CompanyDto companyDto = getCompanyDto();
        companyDto.setEmail(null);

        mockMvc.perform(post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDto))
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("email", "must not be null"));
    }

    private CompanyDto getCompanyDto() {
        return CompanyDto.builder()
                .email("test@email.com")
                .name("TEST OU")
                .phone(5125125)
                .build();
    }
}

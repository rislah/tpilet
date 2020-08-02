package com.rislah.tpilet.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.flyway.enabled=true"
})
@ActiveProfiles("test")
public class CompanyIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testAddCompanyIfSuccess() throws Exception {
        CompanyDto companyDto = getCompanyDto();
        assertThat(companyRepository.existsCompanyByName(companyDto.getName())).isFalse();

        this.mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDto)))
                .andExpect(status().isCreated());

        assertThat(companyRepository.existsCompanyByName(companyDto.getName())).isTrue();
    }

    private Company getCompany() {
        return Company.builder()
                .email("email@gmail.com")
                .name("test ou")
                .phone(5125125)
                .build();
    }

    private CompanyDto getCompanyDto() {
        return CompanyDto.builder()
                .email("test@email.com")
                .name("TEST OU")
                .phone(5125125)
                .build();
    }
}

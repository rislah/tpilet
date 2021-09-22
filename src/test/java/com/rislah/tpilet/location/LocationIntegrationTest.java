package com.rislah.tpilet.location;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rislah.tpilet.dto.LocationDto;
import com.rislah.tpilet.model.Location;
import com.rislah.tpilet.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.flyway.enabled=true"
})
@ActiveProfiles("test")
public class LocationIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LocationRepository locationRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void TestAddLocationIfSuccess() throws Exception {
        LocationDto locationDto = getLocationDto();
        locationDto.setName("Tallinn");
        assertThat(locationRepository.existsByName(locationDto.getName())).isFalse();

        this.mockMvc.perform(post("/api/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(locationDto)))
                .andExpect(status().isCreated());

        assertThat(locationRepository.existsByName(locationDto.getName())).isTrue();
    }

    @Test
    void TestSearchLocationByNameIfSuccess() throws Exception {
        Location location = getLocation();
        location.setName("TestLocation");
        locationRepository.save(location);
        assertThat(locationRepository.existsByName(location.getName())).isTrue();

        String searchQuery = location.getName().substring(0, location.getName().length() - 3);

        MvcResult mvcResult = this.mockMvc.perform(get("/api/locations")
                .param("q", searchQuery)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<Location> locations = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<Location>>() {
                });
        assertThat(locations).contains(location);
    }

    private Location getLocation() {
        return Location.builder().name("Tartu bussijaam").build();
    }

    private LocationDto getLocationDto() {
        return LocationDto.builder().name("Tartu bussijaam").build();
    }
}

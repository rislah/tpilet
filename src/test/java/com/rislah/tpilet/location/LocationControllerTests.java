package com.rislah.tpilet.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rislah.tpilet.controller.LocationController;
import com.rislah.tpilet.dto.LocationDto;
import com.rislah.tpilet.mapper.LocationMapper;
import com.rislah.tpilet.model.Location;
import com.rislah.tpilet.service.LocationService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {LocationController.class, LocationMapper.class})
@ActiveProfiles("test")
public class LocationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LocationMapper locationMapper;

    @MockBean
    private LocationService locationService;

    @Test
    void testSearchLocationByNameIfMapsToBusinessModel() throws Exception {
        LocationDto locationDto = getLocationDto();
        mockMvc.perform(get("/locations")
                .param("q", locationDto.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        ArgumentCaptor<String> locationArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(locationService, times(1)).findLocationsByName(locationArgumentCaptor.capture());

        assertThat(locationDto.getName()).isEqualTo(locationArgumentCaptor.getValue());
    }

    @Test
    void testSearchLocationByNameIfSuccess() throws Exception {
        LocationDto location = getLocationDto();
        List<LocationDto> locations = new ArrayList<>();
        locations.add(location);

        when(locationService.findLocationsByName(location.getName())).thenReturn(locations);

        mockMvc.perform(get("/locations")
                .param("q", location.getName())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(locations), false));
    }

    private LocationDto getLocationDto() {
        return LocationDto.builder()
                .name("Tartu bussijaam")
                .build();
    }
}

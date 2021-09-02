package com.rislah.tpilet.location;

import com.rislah.tpilet.dto.LocationDto;
import com.rislah.tpilet.exception.LocationExistsException;
import com.rislah.tpilet.exception.LocationNotFoundException;
import com.rislah.tpilet.model.Location;
import com.rislah.tpilet.repository.LocationRepository;
import com.rislah.tpilet.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
public class LocationServiceTests {
    @Autowired
    private LocationService locationService;

    @MockBean
    private LocationRepository locationRepository;

    @Test
    public void testAddLocationsIfLocationExists() {
        LocationDto location = getLocationDto();
        when(locationRepository.existsByName(location.getName())).thenReturn(true);
        assertThrows(LocationExistsException.class, () -> {
            locationService.create(location);
        });
    }

    @Test
    void testGetAllLocationsIfNotFound() {
        when(locationRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(LocationNotFoundException.class, () -> {
            locationService.findAll();
        });
    }

    private LocationDto getLocationDto() {
        return LocationDto.builder()
                .name("Tartu bussijaam")
                .build();
    }
}

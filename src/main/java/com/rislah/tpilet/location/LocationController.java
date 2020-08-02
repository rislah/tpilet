package com.rislah.tpilet.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LocationController {
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @Autowired
    public LocationController(LocationService locationService, LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    @PostMapping(path = "/locations", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addLocation(@RequestBody @Valid LocationDto locationDto) {
        locationService.addLocation(locationMapper.locationDtoToLocation(locationDto));
    }

    @GetMapping(path = "/locations", params = "q")
    public List<Location> searchLocationByName(@RequestParam("q") String name) {
        return locationService.searchLocationByName(name);
    }

    @GetMapping(path = "/locations")
    public List<LocationDto> getAllLocations() {
        return locationMapper.locationsToLocationDtos(locationService.getAllLocations());
    }
}

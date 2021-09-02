package com.rislah.tpilet.controller;

import com.rislah.tpilet.dto.LocationDto;
import com.rislah.tpilet.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {
    private final LocationService service;

    @Autowired
    public LocationController(LocationService service) {
        this.service = service;
    }

    @PostMapping(path = "/locations", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto create(@RequestBody @Valid LocationDto locationDto) {
        return service.create(locationDto);
    }

    @GetMapping(path = "/locations", params = "q")
    public List<LocationDto> searchLocationByName(@RequestParam("q") String name) {
        return service.findLocationsByName(name);
    }

    @GetMapping(path = "/locations")
    public List<LocationDto> findAll() {
        return service.findAll();
    }
}

package com.rislah.tpilet.service;

import com.rislah.tpilet.dto.LocationDto;
import com.rislah.tpilet.error.NotFoundException;
import com.rislah.tpilet.error.RecordAlreadyExistsException;
import com.rislah.tpilet.exception.LocationExistsException;
import com.rislah.tpilet.exception.LocationNotFoundException;
import com.rislah.tpilet.mapper.LocationMapper;
import com.rislah.tpilet.model.Location;
import com.rislah.tpilet.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;

    @Autowired
    public LocationService(LocationRepository repository, LocationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public LocationDto create(LocationDto dto) {
        if (repository.existsByName(dto.getName())) {
            throw new LocationExistsException();
        }
        Location location = mapper.locationDtoToLocation(dto);
        repository.saveAndFlush(location);
        return dto;
    }

    public List<LocationDto> findLocationsByName(String name) {
        List<LocationDto> locations = repository.findLocationByNameStartsWith(name)
                .stream()
                .map(mapper::locationToLocationDto)
                .collect(Collectors.toList());
        if (locations.size() == 0) {
            throw new LocationNotFoundException();
        }
        return locations;
    }

    public List<LocationDto> findAll() {
        List<LocationDto> locations = repository.findAll()
                .stream()
                .map(mapper::locationToLocationDto)
                .collect(Collectors.toList());
        if (locations.size() == 0) {
            throw new LocationNotFoundException();
        }
        return locations;
    }
}

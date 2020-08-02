package com.rislah.tpilet.location;

import com.rislah.tpilet.error.NotFoundException;
import com.rislah.tpilet.error.RecordAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void addLocation(Location location) {
        if (locationRepository.existsByName(location.getName())) {
            throw new RecordAlreadyExistsException("Location already exists");
        }
        locationRepository.save(location);
    }

    public List<Location> searchLocationByName(String name) {
        return locationRepository.findLocationByNameStartsWith(name);
    }

    public List<Location> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        if (locations.size() == 0) {
            throw new NotFoundException("Locations not found");
        }
        return locations;
    }
}

package com.rislah.tpilet.location;

import com.rislah.tpilet.model.Location;
import com.rislah.tpilet.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.flyway.enabled=true"
})
@ActiveProfiles("test")
public class LocationRepositoryTests {
    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void testFindLocationByNameStartsWithIfFound() {
        Location location = getLocation();
        String searchName = location.getName().substring(0, location.getName().length() - 5);
        locationRepository.save(location);
        assertThat(locationRepository.findLocationByNameStartsWith(searchName).contains(location));
    }

    @Test
    public void testFindLocationByNameStartsWithIfNotFound() {
        Location location = getLocation();
        locationRepository.save(location);
        assertThat(locationRepository.findLocationByNameStartsWith("Trt")).isEmpty();
    }

    private Location getLocation() {
        return Location.builder().name("Tartu bussijaam").build();
    }
}

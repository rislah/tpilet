package com.rislah.tpilet.repository;

import com.rislah.tpilet.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    boolean existsByName(String name);

    Optional<Location> findByName(String name);

    List<Location> findLocationByNameStartsWith(String name);
}

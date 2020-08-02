package com.rislah.tpilet.route;

import com.rislah.tpilet.bus.BusRepository;
import com.rislah.tpilet.bus.exceptions.BusNotFoundException;
import com.rislah.tpilet.location.Location;
import com.rislah.tpilet.location.LocationRepository;
import com.rislah.tpilet.location.exceptions.LocationNotFoundException;
import com.rislah.tpilet.route.exceptions.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final LocationRepository locationRepository;
    private final BusRepository busRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository, LocationRepository locationRepository, BusRepository busRepository) {
        this.routeRepository = routeRepository;
        this.locationRepository = locationRepository;
        this.busRepository = busRepository;
    }

    public List<Route> findAll() {
        List<Route> routes = routeRepository.findAll();
        if (routes.size() == 0) {
            throw new RouteNotFoundException();
        }
        return routes;
    }

    public void addRoute(Route route) {
        String from = route.getFrom().getName();
        String to = route.getTo().getName();

        Optional<Location> fromLocation = locationRepository.findByName(from);
        if (fromLocation.isEmpty()) {
            throw new LocationNotFoundException(from);
        }

        Optional<Location> toLocation = locationRepository.findByName(to);
        if (toLocation.isEmpty()) {
            throw new LocationNotFoundException(to);
        }

        int busId = route.getBusId();

        if (!busRepository.existsBusById(busId)) {
            throw new BusNotFoundException();
        }

        route.setFrom(fromLocation.get());
        route.setTo(toLocation.get());

        routeRepository.save(route);
    }
}

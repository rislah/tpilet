package com.rislah.tpilet.service;

import com.rislah.tpilet.dto.RouteDto;
import com.rislah.tpilet.exception.BusNotFoundException;
import com.rislah.tpilet.exception.LocationNotFoundException;
import com.rislah.tpilet.exception.RouteNotFoundException;
import com.rislah.tpilet.mapper.RouteMapper;
import com.rislah.tpilet.model.Location;
import com.rislah.tpilet.model.Route;
import com.rislah.tpilet.repository.BusRepository;
import com.rislah.tpilet.repository.LocationRepository;
import com.rislah.tpilet.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final LocationRepository locationRepository;
    private final BusRepository busRepository;
    private final RouteMapper routeMapper;

    @Autowired
    public RouteService(RouteRepository routeRepository, LocationRepository locationRepository, BusRepository busRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.locationRepository = locationRepository;
        this.busRepository = busRepository;
        this.routeMapper = routeMapper;
    }

    public List<RouteDto> findAll() {
        List<RouteDto> routes = routeRepository.findAll()
                .stream()
                .map(routeMapper::routeToRouteDto)
                .collect(Collectors.toList());
        if (routes.size() == 0) {
            throw new RouteNotFoundException();
        }
        return routes;
    }

    @Transactional
    public RouteDto create(RouteDto routeDto) {
        String from = routeDto.getFrom();
        String to = routeDto.getTo();

        Optional<Location> fromLocation = locationRepository.findByName(from);
        if (fromLocation.isEmpty()) {
            throw new LocationNotFoundException(from);
        }

        Optional<Location> toLocation = locationRepository.findByName(to);
        if (toLocation.isEmpty()) {
            throw new LocationNotFoundException(to);
        }

        int busId = routeDto.getBusId();

        if (!busRepository.existsBusById(busId)) {
            throw new BusNotFoundException();
        }

        Route route = routeMapper.routeDtoToRoute(routeDto);
        route.setFrom(fromLocation.get());
        route.setTo(toLocation.get());

        routeRepository.saveAndFlush(route);
        return routeDto;
    }
}

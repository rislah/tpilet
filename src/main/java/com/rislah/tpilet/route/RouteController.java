package com.rislah.tpilet.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RouteController {
    private final RouteMapper routeMapper;
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteMapper routeMapper, RouteService routeService) {
        this.routeMapper = routeMapper;
        this.routeService = routeService;
    }

    @GetMapping(value = "/routes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RouteDto> getAllRoutes() {
        return routeMapper.routesToRouteDtos(routeService.findAll());
    }

    @PostMapping(value = "/routes", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoute(@RequestBody @Valid RouteDto routeDto) {
        routeService.addRoute(routeMapper.routeDtoToRoute(routeDto));
    }
}

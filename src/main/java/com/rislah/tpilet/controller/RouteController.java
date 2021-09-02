package com.rislah.tpilet.controller;

import com.rislah.tpilet.dto.RouteDto;
import com.rislah.tpilet.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RouteController {
    private final RouteService service;

    @Autowired
    public RouteController(RouteService routeService) {
        this.service = routeService;
    }

    @PostMapping(value = "/routes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RouteDto create(@RequestBody @Valid RouteDto routeDto) {
        return service.create(routeDto);
    }

    @GetMapping(value = "/routes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RouteDto> findAll() {
        return service.findAll();
    }
}

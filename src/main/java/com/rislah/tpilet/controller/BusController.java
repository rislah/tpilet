package com.rislah.tpilet.controller;

import com.rislah.tpilet.dto.BusDto;
import com.rislah.tpilet.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BusController {
    private final BusService service;

    @Autowired
    public BusController(BusService service) {
        this.service = service;
    }

    @PostMapping(path = "/buses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BusDto create(@RequestBody @Valid BusDto busDto) {
        return service.create(busDto);
    }

    @GetMapping(path = "/buses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BusDto> findAll() {
        return service.findAll();
    }
}

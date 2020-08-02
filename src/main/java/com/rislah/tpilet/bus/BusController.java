package com.rislah.tpilet.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BusController {
    private final BusService busService;
    private final BusMapper busMapper;

    @Autowired
    public BusController(BusService busService, BusMapper busMapper) {
        this.busService = busService;
        this.busMapper = busMapper;
    }

    @PostMapping(path = "/buses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addBus(@RequestBody @Valid BusDto busDto) {
        busService.addBus(this.busMapper.busDtoToBus(busDto));
    }

    @GetMapping(path = "/buses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BusDto> getAllBuses() {
        return this.busMapper.busesToBusDtos(busService.findAll());
    }
}

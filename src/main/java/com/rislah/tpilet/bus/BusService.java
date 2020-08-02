package com.rislah.tpilet.bus;

import com.rislah.tpilet.bus.exceptions.BusExistsException;
import com.rislah.tpilet.bus.exceptions.BusNotFoundException;
import com.rislah.tpilet.error.NotFoundException;
import com.rislah.tpilet.error.RecordAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService  {
    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public void addBus(Bus bus) {
        if (busRepository.existsBusByNumber(bus.getNumber())) {
            throw new BusExistsException();
        }
        busRepository.save(bus);
    }

    public List<Bus> findAll() {
        List<Bus> buses = busRepository.findAll();
        if (buses.size() == 0 ) {
            throw new BusNotFoundException();
        }
        return buses;
    }
}

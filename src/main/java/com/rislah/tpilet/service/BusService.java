package com.rislah.tpilet.service;

import com.rislah.tpilet.dto.BusDto;
import com.rislah.tpilet.exception.BusExistsException;
import com.rislah.tpilet.exception.BusNotFoundException;
import com.rislah.tpilet.exception.CompanyNotFoundException;
import com.rislah.tpilet.mapper.BusMapper;
import com.rislah.tpilet.model.Bus;
import com.rislah.tpilet.repository.BusRepository;
import com.rislah.tpilet.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {
    private final BusRepository busRepository;
    private final CompanyRepository companyRepository;
    private final BusMapper mapper;

    @Autowired
    public BusService(BusRepository busRepository, CompanyRepository companyRepository, BusMapper mapper) {
        this.busRepository = busRepository;
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    @Transactional
    public BusDto create(BusDto dto) {
        if (busRepository.existsBusByNumber(dto.getNumber())) {
            throw new BusExistsException();
        }
        if (!companyRepository.existsById(dto.getCompanyId())) {
            throw new CompanyNotFoundException();
        }
        Bus bus = mapper.busDtoToBus(dto);
        busRepository.saveAndFlush(bus);
        return dto;
    }

    public List<BusDto> findAll() {
        List<BusDto> buses = busRepository.findAll()
                .stream()
                .map(mapper::busToBusDto)
                .collect(Collectors.toList());
        if (buses.size() == 0) {
            throw new BusNotFoundException();
        }
        return buses;
    }
}

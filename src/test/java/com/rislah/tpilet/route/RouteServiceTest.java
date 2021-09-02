package com.rislah.tpilet.route;

import com.rislah.tpilet.dto.RouteDto;
import com.rislah.tpilet.repository.BusRepository;
import com.rislah.tpilet.exception.BusNotFoundException;
import com.rislah.tpilet.model.Location;
import com.rislah.tpilet.repository.LocationRepository;
import com.rislah.tpilet.exception.LocationNotFoundException;
import com.rislah.tpilet.model.Route;
import com.rislah.tpilet.repository.RouteRepository;
import com.rislah.tpilet.exception.RouteNotFoundException;
import com.rislah.tpilet.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RouteServiceTest {
    @MockBean
    private RouteRepository routeRepository;

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private BusRepository busRepository;

    @Autowired
    private RouteService routeService;

    @Test
    void testFindAllIfNoneFound() {
        when(routeRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(RouteNotFoundException.class, () -> {
            routeService.findAll();
        });
    }

    @Test
    void testAddRouteIfFromLocationNotFound() {
        RouteDto route = getRouteDto();
        String from = route.getFrom();
        when(locationRepository.findByName(from)).thenReturn(Optional.empty());

        Exception exception = assertThrows(LocationNotFoundException.class, () -> {
            routeService.create(route);
        });
        assertThat(exception.getMessage()).contains(from);
    }

    @Test
    void testAddRouteIfToLocationNotFound() {
        RouteDto route = getRouteDto();
        String to = route.getTo();
        String from = route.getFrom();
        when(locationRepository.findByName(from)).thenReturn(Optional.of(new Location()));
        when(locationRepository.findByName(to)).thenReturn(Optional.empty());

        Exception exception = assertThrows(LocationNotFoundException.class, () -> {
            routeService.create(route);
        });
        assertThat(exception.getMessage()).contains(to);
    }

    @Test
    void testAddRouteIfBusNotFound() {
        RouteDto route = getRouteDto();
        when(locationRepository.findByName(route.getFrom())).thenReturn(Optional.of(new Location()));
        when(locationRepository.findByName(route.getTo())).thenReturn(Optional.of(new Location()));
        when(busRepository.existsBusById(route.getBusId())).thenReturn(false);
        assertThrows(BusNotFoundException.class, () -> {
            routeService.create(route);
        });
    }

    private RouteDto getRouteDto() {
        return RouteDto.builder()
                .busId(1)
                .from("Tallinn")
                .to("tartu")
                .arrivalDate(LocalDateTime.now().toString())
                .departureDate(LocalDateTime.now().toString())
                .price(new BigDecimal("12.22"))
                .build();
    }
}

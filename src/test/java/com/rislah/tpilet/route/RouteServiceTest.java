package com.rislah.tpilet.route;

import com.rislah.tpilet.bus.BusRepository;
import com.rislah.tpilet.bus.exceptions.BusNotFoundException;
import com.rislah.tpilet.location.Location;
import com.rislah.tpilet.location.LocationRepository;
import com.rislah.tpilet.location.exceptions.LocationNotFoundException;
import com.rislah.tpilet.route.exceptions.RouteNotFoundException;
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
        Route route = getRoute();
        String from = route.getFrom().getName();
        when(locationRepository.findByName(from)).thenReturn(Optional.empty());

        Exception exception = assertThrows(LocationNotFoundException.class, () -> {
            routeService.addRoute(route);
        });
        assertThat(exception.getMessage()).contains(from);
    }

    @Test
    void testAddRouteIfToLocationNotFound() {
        Route route = getRoute();
        String to = route.getTo().getName();
        String from = route.getFrom().getName();
        when(locationRepository.findByName(from)).thenReturn(Optional.of(new Location()));
        when(locationRepository.findByName(to)).thenReturn(Optional.empty());

        Exception exception = assertThrows(LocationNotFoundException.class, () -> {
            routeService.addRoute(route);
        });
        assertThat(exception.getMessage()).contains(to);
    }

    @Test
    void testAddRouteIfBusNotFound() {
        Route route = getRoute();
        when(locationRepository.findByName(route.getFrom().getName())).thenReturn(Optional.of(new Location()));
        when(locationRepository.findByName(route.getTo().getName())).thenReturn(Optional.of(new Location()));
        when(busRepository.existsBusById(route.getBusId())).thenReturn(false);
        assertThrows(BusNotFoundException.class, () -> {
            routeService.addRoute(route);
        });
    }

    private Route getRoute() {
        return Route.builder()
                .busId(1)
                .from(Location.builder().name("from").build())
                .to(Location.builder().name("to").build())
                .arrivalDate(LocalDateTime.now())
                .departureDate(LocalDateTime.now())
                .price(new BigDecimal("12.22"))
                .build();
    }
}

package com.rislah.tpilet.bus;

import com.rislah.tpilet.error.RecordAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BusServiceTest {
    @MockBean
    private BusRepository busRepository;

    @Autowired
    private BusService busService;

    @Test
    void testAddBusIfBusAlreadyExists() {
        Bus bus = getBus();
        when(busRepository.existsBusByNumber(bus.getNumber())).thenReturn(true);
        assertThrows(RecordAlreadyExistsException.class, () -> {
            busService.addBus(bus);
        });
    }

    private Bus getBus() {
        return Bus.builder()
                .ac(true)
                .number(213123)
                .wc(true)
                .wifi(true)
                .build();
    }
}

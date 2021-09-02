package com.rislah.tpilet.bus;

import com.rislah.tpilet.dto.BusDto;
import com.rislah.tpilet.error.RecordAlreadyExistsException;
import com.rislah.tpilet.model.Bus;
import com.rislah.tpilet.repository.BusRepository;
import com.rislah.tpilet.service.BusService;
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
        BusDto bus = getBusDto();
        when(busRepository.existsBusByNumber(bus.getNumber())).thenReturn(true);
        assertThrows(RecordAlreadyExistsException.class, () -> {
            busService.create(bus);
        });
    }

    private BusDto getBusDto() {
        return BusDto.builder()
                .ac(true)
                .number(213123)
                .wc(true)
                .wifi(true)
                .build();
    }

}

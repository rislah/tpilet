package com.rislah.tpilet.bus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rislah.tpilet.controller.BusController;
import com.rislah.tpilet.dto.BusDto;
import com.rislah.tpilet.mapper.BusMapper;
import com.rislah.tpilet.model.Bus;
import com.rislah.tpilet.service.BusService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.rislah.tpilet.ResponseBodyMatchers.responseBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {BusController.class, BusMapper.class})
public class BusControllerTest {
    @Autowired
    private BusMapper busMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BusService busService;

    @Test
    void testAddBusIfMapsToBusinessModel() throws Exception {
        BusDto busDto = getBusDto();
        mockMvc.perform(post("/api/buses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(busDto)))
                .andExpect(status().isCreated());

        ArgumentCaptor<BusDto> busArgumentCaptor = ArgumentCaptor.forClass(BusDto.class);
        verify(busService, times(1)).create(busArgumentCaptor.capture());

        assertThat(busArgumentCaptor.getValue().getNumber()).isEqualTo(busDto.getNumber());
    }

    @Test
    void testAddBusIfCompanyIdNegative() throws Exception {
        BusDto busDto = getBusDto();
        busDto.setCompanyId(-1);
        mockMvc.perform(post("/api/buses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(busDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("companyId",
                        "must be greater than or equal to 0"));
    }

    @Test
    void testAddBusIfNumberNegative() throws Exception {
        BusDto busDto = getBusDto();
        busDto.setNumber(-1);
        mockMvc.perform(post("/buses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(busDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("number",
                        "must be greater than or equal to 0"));
    }

    private BusDto getBusDto() {
        return BusDto.builder()
                .ac(true)
                .wc(true)
                .wifi(true)
                .number(512512)
                .companyId(2)
                .build();
    }
}

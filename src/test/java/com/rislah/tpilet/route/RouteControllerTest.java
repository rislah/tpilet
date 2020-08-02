package com.rislah.tpilet.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rislah.tpilet.bus.BusMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static com.rislah.tpilet.ResponseBodyMatchers.responseBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {RouteController.class, RouteMapper.class, BusMapper.class})
public class RouteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RouteMapper routeMapper;

    @MockBean
    private RouteService routeService;

    @Test
    void TestAddRouteIfMapsToBusinessModel() throws Exception {
        RouteDto routeDto = getRouteDto();

        mockMvc.perform(post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(routeDto)));

        ArgumentCaptor<Route> routeArgumentCaptor = ArgumentCaptor.forClass(Route.class);
        verify(routeService, times(1)).addRoute(routeArgumentCaptor.capture());

        Route route = routeArgumentCaptor.getValue();
        assertThat(route.getBusId()).isEqualTo(routeDto.getBusId());
        assertThat(route.getArrivalDate()).isEqualTo(routeDto.getArrivalDate());
        assertThat(route.getDepartureDate()).isEqualTo(routeDto.getDepartureDate());
        assertThat(route.getPrice()).isEqualTo(routeDto.getPrice());
    }

    @Test
    void TestAddRouteIfSuccess() throws Exception {
        RouteDto routeDto = getRouteDto();
        mockMvc.perform(post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(routeDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void TestAddRouteIfNegativeBusId() throws Exception {
        RouteDto routeDto = getRouteDto();
        routeDto.setBusId(-1);

        mockMvc.perform(post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(routeDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("busId", "must be greater than or equal to 0"));
    }

    @Test
    void TestAddRouteIfToLessThanMinLength() throws Exception {
        RouteDto routeDto = RouteDto.builder()
                .to("Te")
                .busId(1)
                .from("Test2")
                .arrivalDate("2020-08-02T07:04:57.000")
                .departureDate("2020-08-02T07:04:57.000")
                .price(new BigDecimal("12.22"))
                .build();

        mockMvc.perform(post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(routeDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("to", "length must be between 4 and 100"));
    }

    @Test
    void TestAddRouteIfToNull() throws Exception {
        RouteDto routeDto = RouteDto.builder()
                .to(null)
                .busId(1)
                .from("Test2")
                .arrivalDate("2020-08-02T07:04:57.000")
                .departureDate("2020-08-02T07:04:57.000")
                .price(new BigDecimal("12.22"))
                .build();

        mockMvc.perform(post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(routeDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("to", "must not be blank"));
    }

    @Test
    void TestAddRouteIfFromLessThanMinLength() throws Exception {
        RouteDto routeDto = RouteDto.builder()
                .to("Test")
                .busId(1)
                .from("Te2")
                .arrivalDate("2020-08-02T07:04:57.000")
                .departureDate("2020-08-02T07:04:57.000")
                .price(new BigDecimal("12.22"))
                .build();

        mockMvc.perform(post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(routeDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("from", "length must be between 4 and 100"));
    }

    @Test
    void TestAddRouteIfFromNull() throws Exception {
        RouteDto routeDto = RouteDto.builder()
                .to("Test")
                .busId(1)
                .from(null)
                .arrivalDate("2020-08-02T07:04:57.000")
                .departureDate("2020-08-02T07:04:57.000")
                .price(new BigDecimal("12.22"))
                .build();

        mockMvc.perform(post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(routeDto)))
                .andExpect(status().isBadRequest())
                .andExpect(responseBody().containsValidationError("from", "must not be blank"));
    }

    private RouteDto getRouteDto() {
        return RouteDto.builder()
                .to("Test1")
                .busId(1)
                .from("Test2")
                .arrivalDate("2020-08-02T07:04:57.000")
                .departureDate("2020-08-02T07:04:57.000")
                .price(new BigDecimal("12.22"))
                .build();

    }
}

package com.darkhorse.feidegao.infrastructure.externalserviceimpl;

import com.darkhorse.feidegao.domainmodel.FlightInfo;
import com.darkhorse.feidegao.infrastructure.externalserviceimpl.exception.ExternalServiceNotAvailableException;
import com.darkhorse.feidegao.infrastructure.externalserviceimpl.exception.InvalidRequestException;
import com.darkhorse.feidegao.infrastructure.externalserviceimpl.model.FlightResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.darkhorse.feidegao.infrastructure.config.InstantSerializer.parseToString;
import static com.darkhorse.feidegao.infrastructure.externalserviceimpl.FlightInfoServiceImpl.FLIGHT_INFO_URI;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WireMockTest(httpPort = 8080)
class FlightInfoServiceImplTest {
    @Autowired
    private FlightInfoServiceImpl flightInfoService;

    @Test
    public void should_return_flight_info_successfully_when_id_exists() throws JsonProcessingException {
        Instant takeoffAt = Instant.now();
        Instant arriveAt = Instant.now();
        Instant createdAt = Instant.now();
        String id = "1";
        String from = "Beijing";
        String to = "Shanghai";
        FlightResponse flight = new FlightResponse(id, from, to, parseToString(takeoffAt), parseToString(arriveAt), parseToString(createdAt));

        stubFor(get(FLIGHT_INFO_URI + "/" + id).willReturn(okJson(new ObjectMapper().writeValueAsString(flight))));


        FlightInfo result = flightInfoService.getFlightInfoById(id);


        assertEquals(id, result.getId());
        assertEquals(from, result.getFrom());
        assertEquals(to, result.getTo());
        assertEquals(takeoffAt.truncatedTo(ChronoUnit.SECONDS), result.getTakeoffAt());
        assertEquals(arriveAt.truncatedTo(ChronoUnit.SECONDS), result.getArriveAt());
        assertEquals(createdAt.truncatedTo(ChronoUnit.SECONDS), result.getCreatedAt());
    }

    @Test
    public void should_throw_external_service_not_available_exception_when_get_flight_info_and_service_return_5xx() throws JsonProcessingException {
        String id = "id";
        stubFor(get(FLIGHT_INFO_URI + "/" + id).willReturn(serverError()));

        assertThrows(ExternalServiceNotAvailableException.class, () -> flightInfoService.getFlightInfoById(id));
    }

    @Test
    public void should_throw_invalid_request_exception_when_get_flight_info_and_service_return_4xx() throws JsonProcessingException {
        String id = "id";
        stubFor(get(FLIGHT_INFO_URI + "/" + id).willReturn(badRequest()));

        assertThrows(InvalidRequestException.class, () -> flightInfoService.getFlightInfoById(id));
    }
}
package com.darkhorse.feidegao.infrastructure.externalserviceimpl;

import com.darkhorse.feidegao.domainmodel.FlightInfo;
import com.darkhorse.feidegao.infrastructure.externalserviceimpl.exception.ExternalServiceNotAvailableException;
import com.darkhorse.feidegao.infrastructure.externalserviceimpl.exception.InvalidRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.darkhorse.feidegao.infrastructure.config.InstantDeserializer.parseToInstant;
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
        Instant takeoffAt = parseToInstant("2022-03-22 10:30:00");
        Instant arriveAt = parseToInstant("2022-03-22 12:30:00");
        Instant createdAt = parseToInstant("2022-03-21 10:30:00");
        String id = "13";
        String from = "Beijing";
        String to = "Shanghai";

        stubFor(get(FLIGHT_INFO_URI + "/" + id).willReturn(okJson("" +
                "{\n" +
                "   \"id\":\"13\",\n" +
                "   \"from\":\"Beijing\",\n" +
                "   \"to\":\"Shanghai\",\n" +
                "   \"takeoffAt\":\"2022-03-22 10:30:00\",\n" +
                "   \"arriveAt\":\"2022-03-22 12:30:00\",\n" +
                "   \"createdAt\":\"2022-03-21 10:30:00\"\n" +
                "}")));


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
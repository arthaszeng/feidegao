package com.darkhorse.feidegao.infrastructure.externalserviceimpl;

import com.darkhorse.feidegao.domainmodel.PositionAndPrice;
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

import java.util.List;

import static com.darkhorse.feidegao.infrastructure.externalserviceimpl.PositionAndPriceServiceImpl.POSITION_AND_PRICE_URI;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WireMockTest(httpPort = 8082)
class PositionAndPriceServiceImplTest {
    @Autowired
    private PositionAndPriceServiceImpl positionAndPriceService;

    @Test
    void should_retrieve_position_and_price_list_successfully() {
        String id = "id";
        stubFor(get(POSITION_AND_PRICE_URI + "/" + id).willReturn(okJson("" +
                "[\n" +
                "  {\n" +
                "    \"price\": 1000,\n" +
                "    \"availableAmount\": 20,\n" +
                "    \"classType\": \"FIRST_CLASS\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"price\": 500,\n" +
                "    \"availableAmount\": 30,\n" +
                "    \"classType\": \"ECONOMY_CLASS\"\n" +
                "  }\n" +
                "]")));

        List<PositionAndPrice> positionAndPrices = positionAndPriceService.getPriceAndPositionsById(id);

        assertEquals(2, positionAndPrices.size());
    }

    @Test
    public void should_throw_external_service_not_available_exception_when_external_service_returns_5xx() {
        String id = "id";
        stubFor(get(POSITION_AND_PRICE_URI + "/" + id).willReturn(serverError()));

        assertThrows(ExternalServiceNotAvailableException.class, () -> positionAndPriceService.getPriceAndPositionsById(id));
    }

    @Test
    public void should_throw_invalid_request_exception_when_external_service_returns_4xx() {
        String id = "id";
        stubFor(get(POSITION_AND_PRICE_URI + "/" + id).willReturn(badRequest()));

        assertThrows(InvalidRequestException.class, () -> positionAndPriceService.getPriceAndPositionsById(id));
    }

}
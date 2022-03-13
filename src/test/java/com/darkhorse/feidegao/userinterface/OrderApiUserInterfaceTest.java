package com.darkhorse.feidegao.userinterface;

import com.darkhorse.feidegao.domainmodel.AircraftCabin;
import com.darkhorse.feidegao.domainmodel.FlightInfo;
import com.darkhorse.feidegao.domainmodel.PositionAndPrice;
import com.darkhorse.feidegao.domainmodel.Proposal;
import com.darkhorse.feidegao.infrastructure.repositoryimpl.ProposalRepositoryImpl;
import com.darkhorse.feidegao.userinterface.command.CreateOrderCommand;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;

import static com.darkhorse.feidegao.infrastructure.config.InstantDeserializer.parseToInstant;
import static com.darkhorse.feidegao.infrastructure.config.InstantSerializer.parseToString;
import static com.darkhorse.feidegao.infrastructure.externalserviceimpl.FlightInfoServiceImpl.FLIGHT_INFO_URI;
import static com.darkhorse.feidegao.infrastructure.externalserviceimpl.PositionAndPriceServiceImpl.POSITION_AND_PRICE_URI;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@WireMockTest(httpPort = 8080)
class OrderApiUserInterfaceTest {

    @LocalServerPort
    int port;

    @MockBean
    private ProposalRepositoryImpl proposalRepository;

    @BeforeEach
    void beforeEach() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void should_create_order_successfully() {
        String proposalId = "12";

        Mockito.when(proposalRepository.getProposalById("12")).thenReturn(new Proposal("12",
                new FlightInfo("13",
                        "Beijing",
                        "Shanghai",
                        parseToInstant("2022-03-22 10:30:00"),
                        parseToInstant("2022-03-22 12:30:00"),
                        parseToInstant("2022-03-21 10:30:00")),
                new PositionAndPrice(1000, 20, AircraftCabin.FIRST_CLASS)
        ));

        stubFor(get(POSITION_AND_PRICE_URI + "/" + 13).willReturn(okJson("" +
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

        stubFor(get(FLIGHT_INFO_URI + "/" + 13).willReturn(okJson("" +
                "{\n" +
                "   \"id\":\"13\",\n" +
                "   \"from\":\"Beijing\",\n" +
                "   \"to\":\"Shanghai\",\n" +
                "   \"takeoffAt\":\"2022-03-22 10:30:00\",\n" +
                "   \"arriveAt\":\"2022-03-22 12:30:00\",\n" +
                "   \"createdAt\":\"2022-03-21 10:30:00\"\n" +
                "}")));

        final CreateOrderCommand createProductCommand;
        createProductCommand = new CreateOrderCommand(new CreateOrderCommand.ContactorCommand("15888888888"),
                asList(new CreateOrderCommand.PassengerCommand("Alice", "5111111111111111"),
                        new CreateOrderCommand.PassengerCommand("Bob", "5222222222222222")));

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(createProductCommand)
                .post("/proposals/{pid}/orders", proposalId)
                .then()
                .statusCode(201)
                .body("uri", matchesPattern("^/orders/.*$"))
                .body("contactor.phoneNumber", is("15888888888"))
                .body("passengers", hasSize(2))
                .body("passengers[0].name", is("Alice"))
                .body("passengers[0].idNumber", is("5111111111111111"))
                .body("passengers[1].name", is("Bob"))
                .body("passengers[1].idNumber", is("5222222222222222"))
                .body("price", is(2000))
                .body("amount", is(2))
                .body("createdAt", is(parseToString(Instant.now())));
    }
}
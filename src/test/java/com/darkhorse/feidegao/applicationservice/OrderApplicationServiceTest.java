package com.darkhorse.feidegao.applicationservice;

import com.darkhorse.feidegao.domainmodel.*;
import com.darkhorse.feidegao.domainservice.OrderDomainService;
import com.darkhorse.feidegao.domainservice.externalservice.FlightInfoService;
import com.darkhorse.feidegao.domainservice.externalservice.PositionAndPriceService;
import com.darkhorse.feidegao.domainservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

class OrderApplicationServiceTest {

    private PositionAndPriceService positionAndPriceService;
    private FlightInfoService flightInfoService;
    private OrderRepository orderRepository;
    private OrderApplicationService orderApplicationService;

    @BeforeEach
    void setUp() {
        positionAndPriceService = mock(PositionAndPriceService.class);
        flightInfoService = mock(FlightInfoService.class);
        orderRepository = spy(OrderRepository.class);
        orderApplicationService = new OrderApplicationService(new OrderDomainService(), positionAndPriceService, flightInfoService, orderRepository);
    }

    @Test
    void should_and_save_order_when_create_order_given_valid_input() {
        String flightId = "1";
        FlightInfo flightInfo = new FlightInfo(flightId, "Beijing", "Shanghai", Instant.now(), Instant.now(), Instant.now());
        Contactor contactor = new Contactor("1", "15888888888");
        PositionAndPrice firstClassPositionAndPrice = new PositionAndPrice(1, 10, AircraftCabin.FIRST_CLASS);
        Proposal proposal = new Proposal("1", flightInfo, firstClassPositionAndPrice);
        List<Passenger> passengers = asList(new Passenger("1", "Alice", "123"), new Passenger("1", "Bob", "456"));

        when(positionAndPriceService.getPriceAndPositionsById(flightId)).thenReturn(Collections.singletonList(firstClassPositionAndPrice));
        when(flightInfoService.getFlightInfoById(flightId)).thenReturn(flightInfo);

        orderApplicationService.createOrder(proposal, contactor, passengers);

        verify(orderRepository).save(any(Order.class));
    }
}
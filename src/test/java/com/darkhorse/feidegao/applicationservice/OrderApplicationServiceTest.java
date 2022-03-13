package com.darkhorse.feidegao.applicationservice;

import com.darkhorse.feidegao.domainmodel.*;
import com.darkhorse.feidegao.domainservice.OrderDomainService;
import com.darkhorse.feidegao.domainservice.externalservice.FlightInfoService;
import com.darkhorse.feidegao.domainservice.externalservice.PaymentGateway;
import com.darkhorse.feidegao.domainservice.externalservice.PositionAndPriceService;
import com.darkhorse.feidegao.domainservice.repository.OrderRepository;
import com.darkhorse.feidegao.domainservice.repository.ProposalRepository;
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
    private ProposalRepository proposalRepository;
    private OrderApplicationService orderApplicationService;
    private PaymentGateway paymentGateway;

    @BeforeEach
    void setUp() {
        paymentGateway = mock(PaymentGateway.class);
        positionAndPriceService = mock(PositionAndPriceService.class);
        flightInfoService = mock(FlightInfoService.class);
        proposalRepository = mock(ProposalRepository.class);
        orderRepository = spy(OrderRepository.class);
        orderApplicationService = new OrderApplicationService(paymentGateway,
                new OrderDomainService(),
                proposalRepository,
                positionAndPriceService,
                flightInfoService,
                orderRepository);
    }

    @Test
    void should_and_save_order_when_create_order_given_valid_input() {
        String flightId = "flightId";
        String proposalId = "proposalId";
        FlightInfo flightInfo = new FlightInfo(flightId, "Beijing", "Shanghai", Instant.now(), Instant.now(), Instant.now());
        Contactor contactor = new Contactor("contractId", "15888888888");
        PositionAndPrice firstClassPositionAndPrice = new PositionAndPrice(1, 10, AircraftCabin.FIRST_CLASS);
        Proposal proposal = new Proposal(proposalId, flightInfo, firstClassPositionAndPrice);
        List<Passenger> passengers = asList(new Passenger("aliceId", "Alice", "123"), new Passenger("bobId", "Bob", "456"));

        when(proposalRepository.getProposalById(proposalId)).thenReturn(proposal);
        when(positionAndPriceService.getPriceAndPositionsById(flightId)).thenReturn(Collections.singletonList(firstClassPositionAndPrice));
        when(flightInfoService.getFlightInfoById(flightId)).thenReturn(flightInfo);

        Order order = orderApplicationService.createOrder(proposalId, contactor, passengers);

        verify(orderRepository).save(order);
    }
}
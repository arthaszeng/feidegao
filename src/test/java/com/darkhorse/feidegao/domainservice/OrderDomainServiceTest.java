package com.darkhorse.feidegao.domainservice;

import com.darkhorse.feidegao.domainmodel.*;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderDomainServiceTest {

    @Test
    void should_generate_an_order_successfully_when_provide_correct_params() {
        OrderDomainService orderDomainService = new OrderDomainService();

        PositionAndPrice positionAndPrice = new PositionAndPrice(1, 2, AircraftCabin.FIRST_CLASS);
        FlightInfo flightInfo = new FlightInfo("1", "a", "b", Instant.now(), Instant.now(), Instant.now());
        Contactor contactor = new Contactor("1", "123456");
        Proposal proposal = new Proposal("1", flightInfo, positionAndPrice);
        Passenger alice = new Passenger("1", "Alice", "111");
        Passenger bob = new Passenger("1", "Bob", "222");
        List<Passenger> passengers = asList(alice, bob);

        Order order = orderDomainService.createOrder(proposal, passengers, contactor);

        assertEquals(contactor, order.getContactor());
        assertEquals(passengers, order.getPassengers());
        assertEquals(passengers.size(), order.getAmount());
        assertEquals(2, order.getPrice());
        assertEquals(proposal.getId(), order.getProposalId());
    }
}
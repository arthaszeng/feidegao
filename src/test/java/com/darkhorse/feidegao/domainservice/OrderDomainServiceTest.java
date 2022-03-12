package com.darkhorse.feidegao.domainservice;

import com.darkhorse.feidegao.domainmodel.Contactor;
import com.darkhorse.feidegao.domainmodel.Order;
import com.darkhorse.feidegao.domainmodel.Passenger;
import com.darkhorse.feidegao.domainmodel.Proposal;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderDomainServiceTest {

    @Test
    void should_generate_an_order_successfully_when_provide_correct_params() {
        OrderDomainService orderDomainService = new OrderDomainService();

        Contactor contactor = new Contactor("123456");
        Proposal proposal = new Proposal(1, 2, 3);
        Passenger alice = new Passenger("Alice", "111");
        Passenger bob = new Passenger("Bob", "222");
        List<Passenger> passengers = asList(alice, bob);

        Order order = orderDomainService.createOrder(proposal, passengers, contactor);

        assertEquals(contactor, order.getContactor());
        assertEquals(passengers, order.getPassengers());
        assertEquals(passengers.size(), order.getAmount());
        assertEquals(passengers.size() * proposal.getPrice(), order.getPrice());
        assertEquals(proposal, order.getProposal());
    }
}
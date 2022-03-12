package com.darkhorse.feidegao.domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    private Proposal proposal;
    private Contactor contactor;

    @BeforeEach
    void setUp() {
        proposal = new Proposal(1, 1, 2);
        contactor = new Contactor("123456");
    }

    @Test
    void should_return_0_when_get_amount_and_price_given_empty_passenger_list() {
        Order order = new Order(contactor, emptyList(), proposal);

        assertEquals(0, order.getAmount());
        assertEquals(0, order.getPrice());
    }

    @Test
    void should_return_correct_amount_and_price_given_valid_passengers_and_proposal() {
        Order order = new Order(contactor, asList(new Passenger("Alice", "123"), new Passenger("Bob", "456")), proposal);

        assertEquals(2, order.getAmount());
        assertEquals(2, order.getPrice());
    }
}
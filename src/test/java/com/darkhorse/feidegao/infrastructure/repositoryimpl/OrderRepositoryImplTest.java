package com.darkhorse.feidegao.infrastructure.repositoryimpl;

import com.darkhorse.feidegao.domainmodel.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class OrderRepositoryImplTest {

    private final OrderRepositoryImpl orderRepository;

    @Autowired
    OrderRepositoryImplTest(OrderRepositoryImpl jpaRepository) {
        this.orderRepository = jpaRepository;
    }

    @Test
    void name() {
        PositionAndPrice positionAndPrice = new PositionAndPrice(1, 2, AircraftCabin.FIRST_CLASS);
        FlightInfo flightInfo = new FlightInfo("1", "a", "b", Instant.now(), Instant.now(), Instant.now());
        Contactor contactor = new Contactor("1", "123456");
        Proposal proposal = new Proposal("1", flightInfo, positionAndPrice);
        Passenger alice = new Passenger("1", "Alice", "111");
        Passenger bob = new Passenger("2", "Bob", "222");
        List<Passenger> passengers = asList(alice, bob);
        PaymentRequest paymentRequest = new PaymentRequest("uri", 2, Instant.now(), Instant.now());

        Order order = new Order(UUID.randomUUID().toString(), contactor, passengers, proposal.getId(), 3000, 2, OrderStatus.CREATED, Instant.now(), paymentRequest);

        orderRepository.save(order);
    }
}
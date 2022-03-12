package com.darkhorse.feidegao.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {
    private final String id;
    private final Contactor contactor;
    private final List<Passenger> passengers;
    private final String proposalId;
    private final int price;
    private final int amount;
    private final OrderStatus status;
    private final Instant createdAt;
}

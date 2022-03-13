package com.darkhorse.feidegao.userinterface.representation;

import com.darkhorse.feidegao.domainmodel.Contactor;
import com.darkhorse.feidegao.domainmodel.Order;
import com.darkhorse.feidegao.domainmodel.Passenger;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.darkhorse.feidegao.infrastructure.config.InstantSerializer.parseToString;

@AllArgsConstructor
@Getter
public class OrderRepresentation {
    private final String uri;
    private final Contactor contactor;
    private final List<Passenger> passengers;
    private final String proposalId;
    private final int price;
    private final int amount;
    private final String status;
    private final String createdAt;

    public static OrderRepresentation from(Order order) {
        String URI_PREFIX = "/orders/";

        return new OrderRepresentation(URI_PREFIX + order.getId(),
                order.getContactor(),
                order.getPassengers(),
                order.getProposalId(),
                order.getPrice(),
                order.getAmount(),
                order.getStatus().toString(),
                parseToString(order.getCreatedAt()));
    }
}

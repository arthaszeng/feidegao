package com.darkhorse.feidegao.domainservice;

import com.darkhorse.feidegao.domainmodel.*;
import com.darkhorse.feidegao.domainmodel.PaymentRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Component
public class OrderDomainService {

    public Order createOrder(Proposal proposal, List<Passenger> passengers, Contactor contactor) {
        int passengerCount = passengers.size();
        int singlePrice = proposal.getPositionAndPrice().getPrice();
        int totalPrice = singlePrice * passengerCount;

        return new Order(UUID.randomUUID().toString(),
                contactor,
                passengers,
                proposal.getId(),
                totalPrice,
                passengerCount,
                OrderStatus.CREATED,
                Instant.now(),
                null
        );
    }

    public Order requestPayment(Order order, PaymentRequest paymentRequest) {
        return order.requestPayment(paymentRequest);
    }
}

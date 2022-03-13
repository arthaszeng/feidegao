package com.darkhorse.feidegao.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {
    private String id;
    private Contactor contactor;
    private List<Passenger> passengers;
    private String proposalId;
    private int price;
    private int amount;
    private OrderStatus status;
    private Instant createdAt;
    private PaymentRequest paymentRequest;

    public Order requestPayment(PaymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
        this.status = this.status.moveToNext();
        return this;
    }
}

package com.darkhorse.feidegao.domainmodel;

import lombok.Getter;

import java.util.List;

@Getter
public class Order {
    private final String contactInfo;
    private final List<Passenger> passengers;
    private final Proposal proposal;
    private final int price;
    private final int amount;

    public Order(String contactInfo, List<Passenger> passengers, Proposal proposal) {
        this.contactInfo = contactInfo;
        this.passengers = passengers;
        this.proposal = proposal;
        this.amount = passengers.size();
        this.price = proposal.getPrice() * passengers.size();
    }
}

package com.darkhorse.feidegao.domainservice;

import com.darkhorse.feidegao.domainmodel.Contactor;
import com.darkhorse.feidegao.domainmodel.Order;
import com.darkhorse.feidegao.domainmodel.Passenger;
import com.darkhorse.feidegao.domainmodel.Proposal;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class OrderDomainService {

    public Order createOrder(Proposal proposal, List<Passenger> passengers, Contactor contactor) {
        return new Order(contactor, passengers, proposal);
    }
}

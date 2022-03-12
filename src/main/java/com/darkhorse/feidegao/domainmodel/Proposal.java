package com.darkhorse.feidegao.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Proposal {
    private String id;
    private FlightInfo flightInfo;
    private PositionAndPrice positionAndPrice;
}

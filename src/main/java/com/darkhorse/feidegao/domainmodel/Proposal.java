package com.darkhorse.feidegao.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Proposal {
    private int id;
    private FlightInfo flightInfo;
    private PositionAndPrice positionAndPrice;
}

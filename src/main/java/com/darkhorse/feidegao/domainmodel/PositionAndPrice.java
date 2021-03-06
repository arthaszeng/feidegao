package com.darkhorse.feidegao.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PositionAndPrice {
    private int price;
    private int availableAmount;
    private AircraftCabin classType;
}

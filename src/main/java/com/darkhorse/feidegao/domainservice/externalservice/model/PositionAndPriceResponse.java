package com.darkhorse.feidegao.domainservice.externalservice.model;

import com.darkhorse.feidegao.domainmodel.AircraftCabin;
import com.darkhorse.feidegao.domainmodel.PositionAndPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PositionAndPriceResponse {
    private int price;
    private int availableAmount;
    private String classType;

    public PositionAndPrice to() {
        return new PositionAndPrice(price, availableAmount, AircraftCabin.valueOf(classType));
    }
}

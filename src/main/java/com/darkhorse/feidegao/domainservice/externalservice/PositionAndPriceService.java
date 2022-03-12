package com.darkhorse.feidegao.domainservice.externalservice;

import com.darkhorse.feidegao.domainmodel.PositionAndPrice;

import java.util.List;

public interface PositionAndPriceService {
    List<PositionAndPrice> getPriceAndPositionsById(String flightId);
}

package com.darkhorse.feidegao.domainservice;

import com.darkhorse.feidegao.domainmodel.PositionAndPrice;

import java.util.List;

public interface PriceAndPositionService {
    List<PositionAndPrice> getPriceAndPositionById(int flightId);
}

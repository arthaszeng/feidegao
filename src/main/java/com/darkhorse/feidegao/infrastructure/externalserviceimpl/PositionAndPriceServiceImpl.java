package com.darkhorse.feidegao.infrastructure.externalserviceimpl;

import com.darkhorse.feidegao.domainmodel.PositionAndPrice;
import com.darkhorse.feidegao.domainservice.externalservice.PositionAndPriceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionAndPriceServiceImpl implements PositionAndPriceService {
    @Override
    public List<PositionAndPrice> getPriceAndPositionsById(String flightId) {
        return null;
    }
}

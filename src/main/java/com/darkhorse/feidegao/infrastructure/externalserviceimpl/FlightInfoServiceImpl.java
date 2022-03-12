package com.darkhorse.feidegao.infrastructure.externalserviceimpl;

import com.darkhorse.feidegao.domainmodel.FlightInfo;
import com.darkhorse.feidegao.domainservice.externalservice.FlightInfoService;
import org.springframework.stereotype.Service;

@Service
public class FlightInfoServiceImpl implements FlightInfoService {
    @Override
    public FlightInfo getFlightInfoById(String flightId) {
        return null;
    }
}

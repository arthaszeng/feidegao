package com.darkhorse.feidegao.domainservice;

import com.darkhorse.feidegao.domainmodel.FlightInfo;

public interface FlightInfoService {
    FlightInfo getFlightInfoById(int flightId);
}

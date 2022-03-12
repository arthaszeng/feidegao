package com.darkhorse.feidegao.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FlightInfo {
    private String from;
    private String to;
    private int takeoffAt;
    private int arriveAt;
}

package com.darkhorse.feidegao.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class FlightInfo {
    private String id;
    private String from;
    private String to;
    private Instant takeoffAt;
    private Instant arriveAt;
    private Instant createdAt;
}

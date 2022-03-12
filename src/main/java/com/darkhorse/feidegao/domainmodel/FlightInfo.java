package com.darkhorse.feidegao.domainmodel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class FlightInfo {
    private String id;
    private String from;
    private String to;
    private Instant takeoffAt;
    private Instant arriveAt;
    private Instant createdAt;
}

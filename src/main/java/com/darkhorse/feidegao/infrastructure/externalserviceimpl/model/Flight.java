package com.darkhorse.feidegao.infrastructure.externalserviceimpl.model;

import com.darkhorse.feidegao.domainmodel.FlightInfo;
import lombok.*;

import static com.darkhorse.feidegao.infrastructure.config.InstantDeserializer.parseToInstant;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Flight {
    private String id;
    private String from;
    private String to;

    private String takeoffAt;

    private String arriveAt;

    private String createdAt;

    public FlightInfo to() {
        return new FlightInfo(id, from, to, parseToInstant(takeoffAt), parseToInstant(arriveAt), parseToInstant(createdAt));
    }
}

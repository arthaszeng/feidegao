package com.darkhorse.feidegao.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentRequest {
    private String uri;
    private int amount;
    private Instant createdAt;
    private Instant expiredAt;
}

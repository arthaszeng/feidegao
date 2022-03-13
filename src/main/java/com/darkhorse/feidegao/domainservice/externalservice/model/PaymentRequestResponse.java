package com.darkhorse.feidegao.domainservice.externalservice.model;

import com.darkhorse.feidegao.domainmodel.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.darkhorse.feidegao.infrastructure.config.InstantDeserializer.parseToInstant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentRequestResponse {
    private String uri;
    private int amount;
    private String createdAt;
    private String expiredAt;

    public PaymentRequest to() {
        return new PaymentRequest(uri, amount, parseToInstant(createdAt), parseToInstant(expiredAt));
    }
}

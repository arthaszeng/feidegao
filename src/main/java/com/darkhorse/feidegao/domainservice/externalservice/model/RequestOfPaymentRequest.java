package com.darkhorse.feidegao.domainservice.externalservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestOfPaymentRequest {
    private int price;
    private String uri;
    private String method;
}

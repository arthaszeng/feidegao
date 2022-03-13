package com.darkhorse.feidegao.domainservice.externalservice;

import com.darkhorse.feidegao.domainservice.externalservice.model.PaymentRequestResponse;
import com.darkhorse.feidegao.domainservice.externalservice.model.RequestOfPaymentRequest;

public interface PaymentGateway {
    PaymentRequestResponse requestPayment(RequestOfPaymentRequest requestOfPaymentRequest);
}

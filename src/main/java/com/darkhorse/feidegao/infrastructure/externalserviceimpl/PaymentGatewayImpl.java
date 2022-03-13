package com.darkhorse.feidegao.infrastructure.externalserviceimpl;

import com.darkhorse.feidegao.domainservice.externalservice.PaymentGateway;
import com.darkhorse.feidegao.domainservice.externalservice.model.PaymentRequestResponse;
import com.darkhorse.feidegao.domainservice.externalservice.model.RequestOfPaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class PaymentGatewayImpl implements PaymentGateway {
    public static String PAYMENT_REQUEST_URI = "/payments";
    private final RestTemplate restTemplate;
    @Value("${external.flight.host}")
    private String paymentGatewayHost;

    public PaymentGatewayImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PaymentRequestResponse requestPayment(RequestOfPaymentRequest requestOfPaymentRequest) {
        String url = paymentGatewayHost + PAYMENT_REQUEST_URI;

        return Objects.requireNonNull(restTemplate.postForObject(url, requestOfPaymentRequest, PaymentRequestResponse.class));
    }
}

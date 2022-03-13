package com.darkhorse.feidegao.infrastructure.config;

import com.darkhorse.feidegao.domainservice.externalservice.exception.ExternalServiceNotAvailableException;
import com.darkhorse.feidegao.domainservice.externalservice.exception.InvalidRequestException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);

        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is4xxClientError()) {
            throw new InvalidRequestException();
        }

        if (statusCode.is5xxServerError()) {
            throw new ExternalServiceNotAvailableException();
        }

        return response;
    }
}
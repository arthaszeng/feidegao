package com.darkhorse.feidegao.infrastructure.externalserviceimpl;

import com.darkhorse.feidegao.domainmodel.FlightInfo;
import com.darkhorse.feidegao.domainservice.externalservice.FlightInfoService;
import com.darkhorse.feidegao.infrastructure.externalserviceimpl.model.FlightResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Configuration
public class FlightInfoServiceImpl implements FlightInfoService {
    public static String FLIGHT_INFO_URI = "/flights";
    private final RestTemplate restTemplate;
    @Value("${external.flight.host}")
    private String flightServiceHost;

    public FlightInfoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // All error handling has been configured inner RestTemplateInterceptor
    @Override
    public FlightInfo getFlightInfoById(String flightId) {
        String url = flightServiceHost + FLIGHT_INFO_URI + "/" + flightId;
        return Objects.requireNonNull(restTemplate.getForObject(url, FlightResponse.class)).to();
    }

}

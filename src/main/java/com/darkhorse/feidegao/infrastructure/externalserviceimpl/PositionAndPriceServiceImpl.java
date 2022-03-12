package com.darkhorse.feidegao.infrastructure.externalserviceimpl;

import com.darkhorse.feidegao.domainmodel.PositionAndPrice;
import com.darkhorse.feidegao.domainservice.externalservice.PositionAndPriceService;
import com.darkhorse.feidegao.infrastructure.externalserviceimpl.model.PositionAndPriceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PositionAndPriceServiceImpl implements PositionAndPriceService {
    public static String POSITION_AND_PRICE_URI = "/position-and-prices";
    private final RestTemplate restTemplate;
    @Value("${external.position-and-price.host}")
    private String host;


    public PositionAndPriceServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // All error handling has been configured inner RestTemplateInterceptor
    @Override
    public List<PositionAndPrice> getPriceAndPositionsById(String flightId) {
        String url = host + POSITION_AND_PRICE_URI + "/" + flightId;

        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url, PositionAndPriceResponse[].class)))
                .map(PositionAndPriceResponse::to)
                .collect(Collectors.toList());
    }
}

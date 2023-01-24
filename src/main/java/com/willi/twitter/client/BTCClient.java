package com.willi.twitter.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

@Component
public class BTCClient {

    private final ObjectMapper objectMapper;

    @Autowired
    public BTCClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<Double> getBTCPrice(String currency){
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.coindesk.com/v1/bpi/currentprice.json");
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        try {
            HttpResponse response = client.execute(httpGet);
            String body = EntityUtils.toString(response.getEntity());
            BTCPriceResponseDTO btcPriceResponseDTO = objectMapper.readValue(body, BTCPriceResponseDTO.class);
            return Optional.ofNullable(btcPriceResponseDTO.getBpi().get(currency)).map(PriceResponseDTO::getRateFloat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

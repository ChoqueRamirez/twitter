package com.willi.twitter.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceResponseDTO {
    private String code;
    @JsonProperty("rate_float")
    private Double rateFloat;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRateFloat() {
        return rateFloat;
    }

    public void setRateFloat(Double rateFloat) {
        this.rateFloat = rateFloat;
    }
}

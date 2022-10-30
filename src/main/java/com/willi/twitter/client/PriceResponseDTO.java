package com.willi.twitter.client;

public class PriceResponseDTO {
    private String code;
    private Double rate_float;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRate_float() {
        return rate_float;
    }

    public void setRate_float(Double rate_float) {
        this.rate_float = rate_float;
    }
}

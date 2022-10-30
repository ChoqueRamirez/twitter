package com.willi.twitter.client;

import java.util.Map;

public class BTCPriceResponseDTO {
    private Map<String, PriceResponseDTO> bpi;

    public Map<String, PriceResponseDTO> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, PriceResponseDTO> bpi) {
        this.bpi = bpi;
    }
}

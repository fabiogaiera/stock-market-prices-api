package com.fabiogaiera.tickerservice.domain;

import lombok.Data;

import java.util.Map;

@Data
public class BillingRequest {

    private Integer customerIdentifier;

    private Map<String, Integer> productIdentifierQuantityMap;

}
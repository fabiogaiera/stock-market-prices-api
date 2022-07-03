package com.fabiogaiera.tickerservice.domain;

import lombok.Data;

@Data
public class Product {

    private Integer productIdentifier;

    private String description;

    private float price;

}
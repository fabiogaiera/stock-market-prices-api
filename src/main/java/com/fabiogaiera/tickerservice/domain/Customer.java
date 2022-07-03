package com.fabiogaiera.tickerservice.domain;

import lombok.Data;

@Data
public class Customer {

    private Integer customerIdentifier;

    private String name;

    private String surname;

}
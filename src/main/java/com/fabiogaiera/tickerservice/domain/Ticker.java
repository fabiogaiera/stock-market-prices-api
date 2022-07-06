package com.fabiogaiera.tickerservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tickerSymbol;

    private String company;

}
package com.fabiogaiera.tickerservice.service;

import com.fabiogaiera.tickerservice.domain.HistoricalPrice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TickerService {


    public HistoricalPrice getHistoricalPrice(String ticker, LocalDate date) {
        return null;
    }

}
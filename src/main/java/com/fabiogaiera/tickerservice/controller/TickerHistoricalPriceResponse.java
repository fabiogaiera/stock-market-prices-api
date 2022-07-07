package com.fabiogaiera.tickerservice.controller;

import com.fabiogaiera.tickerservice.domain.HistoricalPrice;
import com.fabiogaiera.tickerservice.domain.Ticker;
import lombok.Data;

@Data
public class TickerHistoricalPriceResponse {

    private Ticker ticker;
    private HistoricalPrice historicalPrice;

    public TickerHistoricalPriceResponse(Ticker ticker, HistoricalPrice historicalPrice) {
        this.ticker = ticker;
        this.historicalPrice = historicalPrice;
    }

}
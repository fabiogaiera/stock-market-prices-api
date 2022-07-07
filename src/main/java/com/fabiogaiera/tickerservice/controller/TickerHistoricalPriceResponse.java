package com.fabiogaiera.tickerservice.controller;

import com.fabiogaiera.tickerservice.entity.HistoricalPrice;
import com.fabiogaiera.tickerservice.entity.Ticker;
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
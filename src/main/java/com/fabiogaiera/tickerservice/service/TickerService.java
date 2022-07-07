package com.fabiogaiera.tickerservice.service;

import com.fabiogaiera.tickerservice.entity.HistoricalPrice;
import com.fabiogaiera.tickerservice.entity.Ticker;
import com.fabiogaiera.tickerservice.repository.HistoricalPriceRepository;
import com.fabiogaiera.tickerservice.repository.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TickerService {

    private HistoricalPriceRepository historicalPriceRepository;

    private TickerRepository tickerRepository;

    public Ticker getTickerInformation(String ticker) {
        return tickerRepository.findByTickerSymbol(ticker);
    }

    public HistoricalPrice getHistoricalPrice(String ticker, LocalDate date) {
        return historicalPriceRepository.findByTickerAndDate(ticker, date);
    }

    @Autowired
    public void setHistoricalPriceRepository(HistoricalPriceRepository historicalPriceRepository) {
        this.historicalPriceRepository = historicalPriceRepository;
    }

    @Autowired
    public void setTickerRepository(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

}
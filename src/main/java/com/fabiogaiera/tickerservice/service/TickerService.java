package com.fabiogaiera.tickerservice.service;

import com.fabiogaiera.tickerservice.domain.HistoricalPrice;
import com.fabiogaiera.tickerservice.repository.HistoricalPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TickerService {

    private HistoricalPriceRepository historicalPriceRepository;

    public HistoricalPrice getHistoricalPrice(String ticker, LocalDate date) {
        return historicalPriceRepository.findByTickerAndDate(ticker, date);
    }

    @Autowired
    public void setHistoricalPriceRepository(HistoricalPriceRepository historicalPriceRepository) {
        this.historicalPriceRepository = historicalPriceRepository;
    }

}
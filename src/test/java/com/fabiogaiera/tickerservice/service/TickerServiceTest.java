package com.fabiogaiera.tickerservice.service;

import com.fabiogaiera.tickerservice.entity.HistoricalPrice;
import com.fabiogaiera.tickerservice.entity.Ticker;
import com.fabiogaiera.tickerservice.repository.HistoricalPriceRepository;
import com.fabiogaiera.tickerservice.repository.TickerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TickerServiceTest {

    @InjectMocks
    private TickerService tickerService;

    @Mock
    private HistoricalPriceRepository historicalPriceRepository;

    @Mock
    private TickerRepository tickerRepository;

    private final String appleTickerSymbol = "AAPL";

    @Test
    public void testGetTickerInformation() {

        Ticker ticker = new Ticker();
        when(tickerRepository.findByTickerSymbol(anyString())).thenReturn(ticker);

        tickerService.getTickerInformation(appleTickerSymbol);

        verify(tickerRepository, times(1)).findByTickerSymbol(appleTickerSymbol);

    }

    @Test
    public void testGetHistoricalPrice() {

        HistoricalPrice historicalPrice = new HistoricalPrice();
        when(historicalPriceRepository.findByTickerAndDate(anyString(), any(LocalDate.class))).thenReturn(historicalPrice);

        tickerService.getHistoricalPrice(appleTickerSymbol, LocalDate.of(2022, 12, 1));

        verify(historicalPriceRepository, times(1)).findByTickerAndDate(anyString(), any(LocalDate.class));

    }

}
package com.fabiogaiera.tickerservice.controller;

import com.fabiogaiera.tickerservice.entity.HistoricalPrice;
import com.fabiogaiera.tickerservice.entity.Ticker;
import com.fabiogaiera.tickerservice.service.FileService;
import com.fabiogaiera.tickerservice.service.TickerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TickerControllerTest {

    @InjectMocks
    private TickerController tickerController;

    @Mock
    private TickerService tickerService;

    @Mock
    private FileService fileService;

    private final String appleTickerSymbol = "AAPL";

    @Test
    public void testPostPrice() throws IOException {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "AAPL.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        doNothing().when(fileService).storageCSVContent(file.getBytes());
        ResponseEntity<?> responseEntity = tickerController.postPrice(file);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);

    }

    @Test
    public void testGetPriceCase01() {

        Ticker tickerInstance = new Ticker();
        HistoricalPrice historicalPrice = new HistoricalPrice();

        when(tickerService.getTickerInformation(anyString())).thenReturn(tickerInstance);
        when(tickerService.getHistoricalPrice(anyString(), any(LocalDate.class))).thenReturn(historicalPrice);

        ResponseEntity<TickerHistoricalPriceResponse> responseEntity = tickerController.getPrice(appleTickerSymbol, LocalDate.of(2022, 7, 17));

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void testGetPriceCase02() {

        HistoricalPrice historicalPrice = new HistoricalPrice();

        when(tickerService.getTickerInformation(anyString())).thenReturn(null);
        when(tickerService.getHistoricalPrice(anyString(), any(LocalDate.class))).thenReturn(historicalPrice);

        ResponseEntity<TickerHistoricalPriceResponse> responseEntity = tickerController.getPrice(appleTickerSymbol, LocalDate.of(2022, 7, 17));

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    @Test
    public void testGetPriceCase03() {

        Ticker tickerInstance = new Ticker();

        when(tickerService.getTickerInformation(anyString())).thenReturn(tickerInstance);
        when(tickerService.getHistoricalPrice(anyString(), any(LocalDate.class))).thenReturn(null);

        ResponseEntity<TickerHistoricalPriceResponse> responseEntity = tickerController.getPrice(appleTickerSymbol, LocalDate.of(2022, 7, 17));

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);

    }

}
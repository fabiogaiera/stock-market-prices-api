package com.fabiogaiera.tickerservice.controller;

import com.fabiogaiera.tickerservice.domain.HistoricalPrice;
import com.fabiogaiera.tickerservice.service.TickerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RestController
public class TickerController {

    private TickerService tickerService;

    private static final Logger logger = LoggerFactory.getLogger(TickerController.class);

    @GetMapping("/getprice")
    public ResponseEntity<TickerHistoricalPriceResponse> getPrice(@RequestParam(name = "ticker")
                                                                  @NotNull
                                                                  @NotEmpty
                                                                  @NotBlank String ticker,

                                                                  @RequestParam(name = "date")
                                                                  @NotNull
                                                                  @NotEmpty
                                                                  @NotBlank
                                                                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {


        logger.debug(String.format("%s%s%s%s", "Start getting price for ticker ", ticker, " and date ", date));

        HistoricalPrice historicalPrice = tickerService.getHistoricalPrice(ticker, date);

        logger.debug(String.format("%s%s%s%s", "End getting price for ticker ", ticker, " and date ", date));

        return new ResponseEntity<>(buildResponse(ticker, historicalPrice), HttpStatus.OK);

    }

    @Autowired
    public void setTickerService(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    private TickerHistoricalPriceResponse buildResponse(String ticker, HistoricalPrice historicalPrice) {
        return null;
    }

}
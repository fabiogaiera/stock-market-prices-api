package com.fabiogaiera.tickerservice.controller;

import com.fabiogaiera.tickerservice.entity.HistoricalPrice;
import com.fabiogaiera.tickerservice.entity.Ticker;
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

@RestController("/api")
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

        Ticker tickerInstance = tickerService.getTickerInformation(ticker);
        HistoricalPrice historicalPrice = tickerService.getHistoricalPrice(ticker, date);

        if ((tickerInstance != null) && (historicalPrice != null)) {
            logger.debug(String.format("%s%s%s%s", "End getting price for ticker ", ticker, " and date ", date));
            return new ResponseEntity<>(buildResponse(tickerInstance, historicalPrice), HttpStatus.OK);
        } else {
            logger.debug(String.format("%s%s%s%s", "Not data found for ticker ", ticker, " and date ", date));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Autowired
    public void setTickerService(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    private TickerHistoricalPriceResponse buildResponse(Ticker ticker, HistoricalPrice historicalPrice) {
        return new TickerHistoricalPriceResponse(ticker, historicalPrice);
    }

}
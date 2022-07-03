package com.fabiogaiera.tickerservice.controller;

import com.fabiogaiera.tickerservice.domain.BillingResponse;
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

import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
public class TickerController {

    private TickerService tickerService;


    private static final Logger logger = LoggerFactory.getLogger(TickerController.class);

    @GetMapping("/getprices")
    public ResponseEntity<BillingResponse> getPrices(@RequestParam(name = "ticker")
                                                     @NotNull String ticker,

                                                     @RequestParam(name = "date")
                                                     @NotNull
                                                     @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        //TODO format date properly
        logger.debug(String.format("%s%s%s%s", "Start getting prices for ticker ", ticker, " and date ", date));

        tickerService.getPrices();

        //TODO format date properly
        logger.debug(String.format("%s%s%s%s", "End getting prices for ticker ", ticker, " and date ", date));

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Autowired
    public void setTickerService(TickerService tickerService) {
        this.tickerService = tickerService;
    }

}
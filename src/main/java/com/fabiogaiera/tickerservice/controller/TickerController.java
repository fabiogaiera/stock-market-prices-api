package com.fabiogaiera.tickerservice.controller;

import com.fabiogaiera.tickerservice.domain.BillingRequest;
import com.fabiogaiera.tickerservice.domain.BillingResponse;
import com.fabiogaiera.tickerservice.service.CustomerProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class TickerController {

    private CustomerProductService customerProductService;

    private static final Logger logger = LoggerFactory.getLogger(TickerController.class);

    @GetMapping("/generatebill")
    public ResponseEntity<BillingResponse> generateBill(@RequestBody @Valid @NotNull BillingRequest billingRequest) {

        logger.info(String.format("%s%s", "Start getting prices for s ", billingRequest.getCustomerIdentifier()));

        BillingResponse billingResponse = new BillingResponse();
        billingResponse.setCustomer(customerProductService.getCustomerDetails(billingRequest.getCustomerIdentifier()));
        billingResponse.setAmount(customerProductService.getBillingAmount(billingRequest.getProductIdentifierQuantityMap()));

        logger.info(String.format("%s%s", "End generating bill for customer identifier ", billingRequest.getCustomerIdentifier()));

        return new ResponseEntity<>(billingResponse, HttpStatus.OK);

    }

    @Autowired
    public void setPoseidonService(CustomerProductService customerProductService) {
        this.customerProductService = customerProductService;
    }

}
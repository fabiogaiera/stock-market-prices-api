package com.fabiogaiera.tickerservice.controller;

import com.fabiogaiera.tickerservice.domain.BillingRequest;
import com.fabiogaiera.tickerservice.domain.BillingResponse;
import com.fabiogaiera.tickerservice.domain.Customer;
import com.fabiogaiera.tickerservice.service.CustomerProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TickerControllerTest {

    @InjectMocks
    private TickerController tickerController;

    @Mock
    private CustomerProductService customerProductService;

    @Test
    public void testGenerateBill() {

        BillingRequest billingRequest = new BillingRequest();
        billingRequest.setCustomerIdentifier(123456);
        billingRequest.setProductIdentifierQuantityMap(new HashMap<>());

        Customer customer = new Customer();
        customer.setName("Fabio");
        customer.setSurname("Rossi");

        float amount = 123.45f;

        // Mocks
        when(customerProductService.getCustomerDetails(billingRequest.getCustomerIdentifier())).thenReturn(customer);
        when(customerProductService.getBillingAmount(billingRequest.getProductIdentifierQuantityMap())).thenReturn(amount);

        // Call to service
        ResponseEntity<BillingResponse> responseEntity = tickerController.generateBill(billingRequest);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customer, Objects.requireNonNull(responseEntity.getBody()).getCustomer());
        assertEquals(amount, responseEntity.getBody().getAmount(), 0.0005);

    }

}
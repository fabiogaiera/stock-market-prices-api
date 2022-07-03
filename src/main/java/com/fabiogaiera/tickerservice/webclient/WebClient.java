package com.fabiogaiera.tickerservice.webclient;

import com.fabiogaiera.tickerservice.domain.Customer;
import com.fabiogaiera.tickerservice.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebClient {

    private RestTemplate restTemplate;

    @Value("${url.productservice}")
    private String urlProductService;

    @Value("${url.customerservice}")
    private String urlCustomerService;

    public Product getProduct(String productIdentifier) {
        return restTemplate.getForObject(
                String.format("%s%s%s", urlProductService, "/?", productIdentifier), Product.class);
    }

    public Customer getCustomer(Integer customerIdentifier) {
        return restTemplate.getForObject(
                String.format("%s%s%d", urlCustomerService, "/?", customerIdentifier), Customer.class);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
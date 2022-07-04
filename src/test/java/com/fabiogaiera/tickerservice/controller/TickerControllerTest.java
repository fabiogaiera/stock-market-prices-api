package com.fabiogaiera.tickerservice.controller;

import com.fabiogaiera.tickerservice.service.TickerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TickerControllerTest {

    @InjectMocks
    private TickerController tickerController;

    @Mock
    private TickerService tickerService;

    @Test
    public void testGetPrice() {

    }

}
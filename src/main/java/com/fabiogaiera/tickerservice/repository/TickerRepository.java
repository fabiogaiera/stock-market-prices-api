package com.fabiogaiera.tickerservice.repository;

import com.fabiogaiera.tickerservice.entity.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TickerRepository extends JpaRepository<Ticker, Long> {

    Ticker findByTickerSymbol(String tickerSymbol);

}

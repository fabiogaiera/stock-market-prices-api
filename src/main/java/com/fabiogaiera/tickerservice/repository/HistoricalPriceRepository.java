package com.fabiogaiera.tickerservice.repository;

import com.fabiogaiera.tickerservice.domain.HistoricalPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricalPriceRepository extends JpaRepository<HistoricalPrice, Long> {

}
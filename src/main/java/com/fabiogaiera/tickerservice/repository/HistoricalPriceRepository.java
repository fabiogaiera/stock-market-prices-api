package com.fabiogaiera.tickerservice.repository;

import com.fabiogaiera.tickerservice.domain.HistoricalPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HistoricalPriceRepository extends JpaRepository<HistoricalPrice, Long> {

    @Query(value = "select hp from HistoricalPrice hp inner join Ticker t on hp.id = t.id and t.tickerSymbol = :ticker and hp.date = :date")
    HistoricalPrice findByTickerAndDate(@Param("ticker") String ticker, @Param("date") LocalDate date);

}
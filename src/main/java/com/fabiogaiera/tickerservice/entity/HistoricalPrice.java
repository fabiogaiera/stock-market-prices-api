package com.fabiogaiera.tickerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class HistoricalPrice {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    @Column(nullable = false, precision = 20, scale = 10)
    private BigDecimal open;

    @Column(nullable = false, precision = 20, scale = 10)
    private BigDecimal high;

    @Column(nullable = false, precision = 20, scale = 10)
    private BigDecimal low;

    @Column(nullable = false, precision = 20, scale = 10)
    private BigDecimal close;

    @Column(nullable = false, precision = 20, scale = 10)
    private BigDecimal adjustedClose;

    private Integer volume;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ticker_id")
    private Ticker ticker;

    public HistoricalPrice() {
    }

    public HistoricalPrice(LocalDate date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal adjustedClose, Integer volume, Ticker ticker) {

        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjustedClose = adjustedClose;
        this.volume = volume;
        this.ticker = ticker;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(BigDecimal adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

}
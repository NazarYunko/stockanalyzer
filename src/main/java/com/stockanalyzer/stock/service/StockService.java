package com.stockanalyzer.stock.service;

import yahoofinance.Stock;
import yahoofinance.histquotes.Interval;

import java.time.Instant;
import java.util.List;

public interface StockService {

    Stock findBy(String symbol);

    Stock findBy(String symbol, String from, String to, Interval interval);

    List<Stock> findAllBy(List<String> symbols, String from, String to, Interval interval);
}

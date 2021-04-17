package com.stockanalyzer.stock.service.yahoo;

import com.stockanalyzer.stock.exception.YahooFinanceException;
import com.stockanalyzer.stock.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class YahooStockService implements StockService {

    @Override
    public Stock findBy(String symbol) {
        try {
            return YahooFinance.get(symbol);
        } catch (IOException e) {
            throw new YahooFinanceException(e.getMessage());
        }
    }

    @Override
    public Stock findBy(String symbol, String from, String to, Interval interval) {
        try {

            if (!from.isBlank() && !to.isBlank()) {
                var fromCalendar = GregorianCalendar.from(ZonedDateTime.ofInstant(Instant.parse(from), ZoneId.systemDefault()));
                var toCalendar = GregorianCalendar.from(ZonedDateTime.ofInstant(Instant.parse(to), ZoneId.systemDefault()));
                return updateExchange(YahooFinance.get(symbol, fromCalendar, toCalendar, interval));
            } else if (to.isBlank() && !from.isBlank()) {
                var fromCalendar = GregorianCalendar.from(ZonedDateTime.ofInstant(Instant.parse(from), ZoneId.systemDefault()));
                return updateExchange(YahooFinance.get(symbol, fromCalendar, interval));
            } else {
                return updateExchange(YahooFinance.get(symbol, interval));
            }

        } catch (IOException e) {
            throw new YahooFinanceException(e.getMessage());
        }
    }

    private Stock updateExchange(Stock stock) {
        if (stock.getStockExchange().contains("Nasdaq")) {
            stock.setStockExchange("NASDAQ");
        }

        return stock;
    }

    @Override
    public List<Stock> findAllBy(List<String> symbols, String from, String to, Interval interval) {
        try {
            var symbolsArray = new String[symbols.size()];

            if (from != null && to != null) {
                var fromCalendar = GregorianCalendar.from(ZonedDateTime.ofInstant(Instant.parse(from), ZoneId.systemDefault()));
                var toCalendar = GregorianCalendar.from(ZonedDateTime.ofInstant(Instant.parse(to), ZoneId.systemDefault()));
                return new ArrayList<>(YahooFinance.get(symbolsArray, fromCalendar, toCalendar, interval).values());
            } else if (to == null && from != null) {
                var fromCalendar = GregorianCalendar.from(ZonedDateTime.ofInstant(Instant.parse(from), ZoneId.systemDefault()));
                return new ArrayList<>(YahooFinance.get(symbolsArray, fromCalendar, interval).values());
            } else {
                return new ArrayList<>(YahooFinance.get(symbolsArray, interval).values());
            }

        } catch (IOException e) {
            throw new YahooFinanceException(e.getMessage());
        }
    }
}

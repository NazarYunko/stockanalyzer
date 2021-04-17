package com.stockanalyzer.ticker.exception;

public class TickerParseFailedException extends RuntimeException {

    public TickerParseFailedException(String message) {
        super(message);
    }
}

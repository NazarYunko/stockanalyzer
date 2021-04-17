package com.stockanalyzer.stock.wire;

public class StockExceptionResponse {

    private final String message;

    public StockExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

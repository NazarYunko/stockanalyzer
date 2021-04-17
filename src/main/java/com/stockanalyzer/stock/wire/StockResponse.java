package com.stockanalyzer.stock.wire;

import yahoofinance.Stock;

public class StockResponse {

    private final Stock stock;

    public StockResponse(Stock stock) {
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }
}

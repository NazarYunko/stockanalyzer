package com.stockanalyzer.ticker.wire;

import java.util.List;

public class TickerResponse {

    private final List<TickerItem> tickers;

    public TickerResponse(List<TickerItem> tickers) {
        this.tickers = tickers;
    }

    public List<TickerItem> getTickers() {
        return tickers;
    }
}

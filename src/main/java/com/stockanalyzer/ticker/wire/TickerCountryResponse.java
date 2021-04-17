package com.stockanalyzer.ticker.wire;

import java.util.List;

public class TickerCountryResponse {

    private final List<TickerCountryItem> tickerCountries;

    public TickerCountryResponse(List<TickerCountryItem> tickerCountries) {
        this.tickerCountries = tickerCountries;
    }

    public List<TickerCountryItem> getTickerCountries() {
        return tickerCountries;
    }
}

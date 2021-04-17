package com.stockanalyzer.ticker.wire;

import java.util.List;

public class TickerIndustryResponse {

    private final List<TickerIndustryItem> tickerIndustries;

    public TickerIndustryResponse(List<TickerIndustryItem> tickerIndustries) {
        this.tickerIndustries = tickerIndustries;
    }

    public List<TickerIndustryItem> getTickerIndustries() {
        return tickerIndustries;
    }
}

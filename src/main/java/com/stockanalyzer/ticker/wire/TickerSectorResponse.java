package com.stockanalyzer.ticker.wire;

import java.util.List;

public class TickerSectorResponse {

    private final List<TickerSectorItem> tickerSectors;

    public TickerSectorResponse(List<TickerSectorItem> tickerSectors) {
        this.tickerSectors = tickerSectors;
    }

    public List<TickerSectorItem> getTickerSectors() {
        return tickerSectors;
    }
}

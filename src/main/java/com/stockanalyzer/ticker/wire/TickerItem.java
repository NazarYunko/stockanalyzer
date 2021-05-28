package com.stockanalyzer.ticker.wire;

import com.stockanalyzer.ticker.domain.Ticker;

public class TickerItem {

    private final long id;

    private final String symbol;

    private final String name;

    private final String country;

    private final String sector;

    private final String industry;

    public TickerItem(long id, String symbol, String name, String country, String sector, String industry) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.country = country;
        this.sector = sector;
        this.industry = industry;
    }

    public TickerItem(Ticker ticker) {
        this.id = ticker.getId();
        this.symbol = ticker.getSymbol();
        this.name = ticker.getName();
        this.country = ticker.getCountry().getName();
        this.sector = ticker.getSector().getName();
        this.industry = ticker.getIndustry().getName();
    }

    public long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getSector() {
        return sector;
    }

    public String getIndustry() {
        return industry;
    }
}

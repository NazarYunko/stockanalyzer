package com.stockanalyzer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "stockanalyzer")
public class ApplicationProperties {

    private boolean parseStocks;

    private String tickerPath;

    public boolean isParseStocks() {
        return parseStocks;
    }

    public void setParseStocks(boolean parseStocks) {
        this.parseStocks = parseStocks;
    }

    public String getTickerPath() {
        return tickerPath;
    }

    public void setTickerPath(String tickerPath) {
        this.tickerPath = tickerPath;
    }
}

package com.stockanalyzer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "stockanalyzer")
public class ApplicationProperties {

    private boolean parseStocks;

    public boolean isParseStocks() {
        return parseStocks;
    }

    public void setParseStocks(boolean parseStocks) {
        this.parseStocks = parseStocks;
    }
}

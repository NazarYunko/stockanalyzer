package com.stockanalyzer.ticker.parser;

import com.stockanalyzer.ticker.domain.Ticker;
import com.stockanalyzer.ticker.exception.TickerParseFailedException;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TickerParser implements ResourceFileParser<List<Ticker>> {
    private static final String SEPARATOR = ",";

    public List<Ticker> parse(String filePath) {
        List<Ticker> tickers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getFile(filePath)))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tickerInfo = line.split(SEPARATOR);
                tickers.add(
                        buildTicker(
                                getTickerInfo(tickerInfo, 0),
                                getTickerInfo(tickerInfo, 1),
                                getTickerInfo(tickerInfo, 6),
                                getTickerInfo(tickerInfo, 9),
                                getTickerInfo(tickerInfo, 10)
                        )
                );
            }
        } catch (IOException e) {
            throw new TickerParseFailedException(e.getMessage());
        }

        return tickers;
    }

    private File getFile(String filePath) throws FileNotFoundException {
        return ResourceUtils.getFile(filePath);
    }

    private Ticker buildTicker(String symbol, String name, String country, String sector, String industry) {
        return new Ticker(symbol, name, country, sector, industry);
    }

    private String getTickerInfo(String[] tickerInfo, int index) {
        try {
            return tickerInfo[index];
        } catch (RuntimeException e) {
            return "<no data>";
        }
    }
}

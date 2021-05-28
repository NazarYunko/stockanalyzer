package com.stockanalyzer.ticker.parser;

import com.stockanalyzer.ticker.domain.Country;
import com.stockanalyzer.ticker.domain.Industry;
import com.stockanalyzer.ticker.domain.Sector;
import com.stockanalyzer.ticker.domain.Ticker;
import com.stockanalyzer.ticker.exception.TickerParseFailedException;
import com.stockanalyzer.ticker.repository.CountryRepository;
import com.stockanalyzer.ticker.repository.IndustryRepository;
import com.stockanalyzer.ticker.repository.SectorRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TickerParser implements ResourceFileParser<List<Ticker>> {
    private static final String SEPARATOR = ",";

    private final CountryRepository countryRepository;
    private final SectorRepository sectorRepository;
    private final IndustryRepository industryRepository;

    public TickerParser(CountryRepository countryRepository, SectorRepository sectorRepository, IndustryRepository industryRepository) {
        this.countryRepository = countryRepository;
        this.sectorRepository = sectorRepository;
        this.industryRepository = industryRepository;
    }

    public List<Ticker> parse(String filePath) {
        List<Ticker> tickers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getFile(filePath)))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tickerInfo = line.split(SEPARATOR);

                String symbol = getTickerInfo(tickerInfo, 0);
                String name = getTickerInfo(tickerInfo, 1);
                String country = getTickerInfo(tickerInfo, 6);
                String sector = getTickerInfo(tickerInfo, 9);
                String industry = getTickerInfo(tickerInfo, 10);

                tickers.add(
                        buildTicker(symbol, name, getCountry(country), getSector(sector), getIndustry(industry))
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

    private Ticker buildTicker(String symbol, String name, Country country, Sector sector, Industry industry) {
        return new Ticker(symbol, name, country, sector, industry);
    }

    private Country getCountry(String name) {
        return countryRepository.findByName(name)
                .orElseGet(() -> countryRepository.save(new Country(name)));
    }

    private Sector getSector(String name) {
        return sectorRepository.findByName(name)
                .orElseGet(() -> sectorRepository.save(new Sector(name)));
    }

    private Industry getIndustry(String name) {
        return industryRepository.findByName(name)
                .orElseGet(() -> industryRepository.save(new Industry(name)));
    }

    private String getTickerInfo(String[] tickerInfo, int index) {
        try {
            return tickerInfo[index];
        } catch (RuntimeException e) {
            return "";
        }
    }
}

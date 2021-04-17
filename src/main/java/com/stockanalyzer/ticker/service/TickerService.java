package com.stockanalyzer.ticker.service;

import com.stockanalyzer.ticker.domain.Ticker;
import com.stockanalyzer.ticker.repository.TickerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TickerService {

    private final TickerRepository tickerRepository;

    public TickerService(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    public List<Ticker> findAllByCountryAndSectorAndIndustry(String country, String sector, String industry) {
        return tickerRepository.findAllByCountryAndSectorAndIndustry(country, sector, industry);
    }

    public Set<String> findAllCountries() {
        return tickerRepository.findAllCountries();
    }

    public Set<String> findAllSectors() {
        return tickerRepository.findAllSectors();
    }

    public Set<String> findAllIndustries() {
        return tickerRepository.findAllIndustries();
    }
}

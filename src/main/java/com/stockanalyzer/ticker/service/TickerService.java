package com.stockanalyzer.ticker.service;

import com.stockanalyzer.ticker.domain.Ticker;
import com.stockanalyzer.ticker.repository.TickerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TickerService {

    private final TickerRepository tickerRepository;

    public TickerService(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    public Page<Ticker> findAllByCountryAndSectorAndIndustry(String country, String sector, String industry, Pageable pageable) {
        return tickerRepository.findAllByCountryAndSectorAndIndustry(country, sector, industry, pageable);
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

package com.stockanalyzer.ticker.service;

import com.stockanalyzer.ticker.domain.Country;
import com.stockanalyzer.ticker.domain.Industry;
import com.stockanalyzer.ticker.domain.Sector;
import com.stockanalyzer.ticker.domain.Ticker;
import com.stockanalyzer.ticker.repository.CountryRepository;
import com.stockanalyzer.ticker.repository.IndustryRepository;
import com.stockanalyzer.ticker.repository.SectorRepository;
import com.stockanalyzer.ticker.repository.TickerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TickerService {

    private final TickerRepository tickerRepository;
    private final CountryRepository countryRepository;
    private final SectorRepository sectorRepository;
    private final IndustryRepository industryRepository;

    public TickerService(TickerRepository tickerRepository, CountryRepository countryRepository, SectorRepository sectorRepository, IndustryRepository industryRepository) {
        this.tickerRepository = tickerRepository;
        this.countryRepository = countryRepository;
        this.sectorRepository = sectorRepository;
        this.industryRepository = industryRepository;
    }

    public Page<Ticker> findAllByCountryAndSectorAndIndustry(String country, String sector, String industry, Pageable pageable) {
        Long countryId = country != null ? countryRepository.findByName(country).get().getId() : null;
        Long sectorId = sector != null ? sectorRepository.findByName(sector).get().getId() : null;
        Long industryId = industry != null ? industryRepository.findByName(industry).get().getId() : null;
        return tickerRepository.findAllByCountryAndSectorAndIndustry(countryId, sectorId, industryId, pageable);
    }

    public Set<String> findAllCountries() {
        return countryRepository.findAll().stream()
                .map(Country::getName)
                .filter(name -> !name.equals(""))
                .collect(Collectors.toSet());
    }

    public Set<String> findAllSectors() {
        return sectorRepository.findAll().stream()
                .map(Sector::getName)
                .filter(name -> !name.equals(""))
                .collect(Collectors.toSet());
    }

    public Set<String> findAllIndustries() {
        return industryRepository.findAll().stream()
                .map(Industry::getName)
                .filter(name -> !name.equals(""))
                .collect(Collectors.toSet());
    }
}

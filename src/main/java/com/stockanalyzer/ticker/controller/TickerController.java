package com.stockanalyzer.ticker.controller;

import com.stockanalyzer.ticker.domain.Ticker;
import com.stockanalyzer.ticker.service.TickerService;
import com.stockanalyzer.ticker.wire.TickerCountryItem;
import com.stockanalyzer.ticker.wire.TickerCountryResponse;
import com.stockanalyzer.ticker.wire.TickerIndustryItem;
import com.stockanalyzer.ticker.wire.TickerIndustryResponse;
import com.stockanalyzer.ticker.wire.TickerItem;
import com.stockanalyzer.ticker.wire.TickerResponse;
import com.stockanalyzer.ticker.wire.TickerSectorItem;
import com.stockanalyzer.ticker.wire.TickerSectorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.stockanalyzer.ticker.controller.TickerRestConstants.TICKERS_COUNTRIES;
import static com.stockanalyzer.ticker.controller.TickerRestConstants.TICKERS_INDUSTRIES;
import static com.stockanalyzer.ticker.controller.TickerRestConstants.TICKERS_ROOT;
import static com.stockanalyzer.ticker.controller.TickerRestConstants.TICKERS_SECTORS;

@RestController
public class TickerController {

    private final TickerService tickerService;

    public TickerController(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @GetMapping(value = TICKERS_ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<TickerItem>> findAllByCountryAndSectorAndIndustry(@RequestParam(value = "country", required = false) String country,
                                                                                 @RequestParam(value = "sector", required = false) String sector,
                                                                                 @RequestParam(value = "industry", required = false) String industry,
                                                                                 Pageable pageable) {
        var tickerPage = tickerService.findAllByCountryAndSectorAndIndustry(country, sector, industry, pageable);
        return ResponseEntity.ok(tickerPage.map(TickerItem::new));
    }


    @GetMapping(value = TICKERS_COUNTRIES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TickerCountryItem>> findAllCountries() {
        var tickerCountries = tickerCountries(tickerService.findAllCountries());
        return ResponseEntity.ok(tickerCountries);
    }

    private List<TickerCountryItem> tickerCountries(Set<String> countries) {
        return countries.stream().map(TickerCountryItem::new).collect(Collectors.toList());
    }

    @GetMapping(value = TICKERS_INDUSTRIES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TickerIndustryItem>> findAllIndustries() {
        var tickerIndustries = tickerIndustries(tickerService.findAllIndustries());
        return ResponseEntity.ok(tickerIndustries);
    }

    private List<TickerIndustryItem> tickerIndustries(Set<String> industries) {
        return industries.stream().map(TickerIndustryItem::new).collect(Collectors.toList());
    }

    @GetMapping(value = TICKERS_SECTORS, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TickerSectorItem>> findAllSectors() {
        var tickerSectors = tickerSectors(tickerService.findAllSectors());
        return ResponseEntity.ok(tickerSectors);
    }

    private List<TickerSectorItem> tickerSectors(Set<String> sectors) {
        return sectors.stream().map(TickerSectorItem::new).collect(Collectors.toList());
    }
}

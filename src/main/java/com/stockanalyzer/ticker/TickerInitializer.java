package com.stockanalyzer.ticker;

import com.stockanalyzer.ApplicationProperties;
import com.stockanalyzer.ticker.domain.Ticker;
import com.stockanalyzer.ticker.parser.ResourceFileParser;
import com.stockanalyzer.ticker.repository.TickerRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TickerInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final ApplicationProperties applicationProperties;
    private final ResourceFileParser<List<Ticker>> tickersParser;
    private final TickerRepository tickerRepository;

    public TickerInitializer(ApplicationProperties applicationProperties,
                             ResourceFileParser<List<Ticker>> tickersParser,
                             TickerRepository tickerRepository) {
        this.applicationProperties = applicationProperties;
        this.tickersParser = tickersParser;
        this.tickerRepository = tickerRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (applicationProperties.isParseStocks()) {
            var tickers = tickersParser.parse(applicationProperties.getTickerPath());
            tickerRepository.saveAll(tickers);
        }
    }
}

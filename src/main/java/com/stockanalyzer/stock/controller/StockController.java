package com.stockanalyzer.stock.controller;

import com.stockanalyzer.stock.service.StockService;
import com.stockanalyzer.stock.wire.StockResponse;
import com.sun.istack.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.histquotes.Interval;

import static com.stockanalyzer.stock.controller.StockRestConstants.RETRIEVE_SINGLE_STOCK;

@RestController //http://localhost:8080/stocks/INTC?interval=DAILY&from=&to=
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(value = RETRIEVE_SINGLE_STOCK, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockResponse> find(@PathVariable("stockSymbol") String symbol,
                                              @RequestParam(value = "from", required = false) String from,
                                              @RequestParam(value = "to", required = false) String to,
                                              @NotNull @RequestParam("interval") Interval interval) {
        var stock = stockService.findBy(symbol, from, to, interval);
        return ResponseEntity.ok(new StockResponse(stock));
    }
}

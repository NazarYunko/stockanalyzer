package com.stockanalyzer.stock.controller.handler;

import com.stockanalyzer.stock.exception.YahooFinanceException;
import com.stockanalyzer.stock.wire.StockExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.stockanalyzer.stock")
public class StockExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(YahooFinanceException.class)
    protected ResponseEntity<StockExceptionResponse> handle(YahooFinanceException ex, WebRequest request) {
        return new ResponseEntity<>(new StockExceptionResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

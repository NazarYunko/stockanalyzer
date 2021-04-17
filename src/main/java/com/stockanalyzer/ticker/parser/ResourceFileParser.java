package com.stockanalyzer.ticker.parser;

public interface ResourceFileParser<T> {

    T parse(String filePath);
}

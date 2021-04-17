package com.stockanalyzer.ticker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String symbol;

    private String name;

    private String country;

    private String sector;

    private String industry;

    public Ticker() {
    }

    public Ticker(String symbol, String name, String country, String sector, String industry) {
        this.symbol = symbol;
        this.name = name;
        this.country = country;
        this.sector = sector;
        this.industry = industry;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticker ticker = (Ticker) o;
        return id == ticker.id &&
                Objects.equals(symbol, ticker.symbol) &&
                Objects.equals(name, ticker.name) &&
                Objects.equals(sector, ticker.sector) &&
                Objects.equals(industry, ticker.industry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, name, sector, industry);
    }
}

package com.stockanalyzer.ticker.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String symbol;

    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Country country;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Sector sector;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Industry industry;

    public Ticker() {
    }

    public Ticker(String symbol, String name, Country country, Sector sector, Industry industry) {
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
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

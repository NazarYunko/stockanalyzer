package com.stockanalyzer.ticker.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "industry")
    private List<Ticker> tickers;

    public Industry() {

    }

    public Industry(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticker> getTickers() {
        return tickers;
    }

    public void setTickers(List<Ticker> tickers) {
        this.tickers = tickers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Industry industry = (Industry) o;
        return name.equals(industry.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

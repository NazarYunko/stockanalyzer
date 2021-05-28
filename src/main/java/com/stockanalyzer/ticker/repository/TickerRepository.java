package com.stockanalyzer.ticker.repository;

import com.stockanalyzer.ticker.domain.Country;
import com.stockanalyzer.ticker.domain.Industry;
import com.stockanalyzer.ticker.domain.Sector;
import com.stockanalyzer.ticker.domain.Ticker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TickerRepository extends JpaRepository<Ticker, Long> {

    Optional<Ticker> findBySymbol(String symbol);

    Optional<Ticker> findByName(String name);

    @Query(value = "SELECT t.* FROM ticker t " +
            "WHERE (:country IS NULL OR t.country_id=:country)" +
            "AND(:sector IS NULL OR t.sector_id=:sector)" +
            "AND(:industry IS NULL OR t.industry_id=:industry)",
            nativeQuery = true,
            countQuery = "SELECT count(*) FROM ticker t " +
                    "WHERE (:country IS NULL OR t.country_id=:country)" +
                    "AND(:sector IS NULL OR t.sector_id=:sector)" +
                    "AND(:industry IS NULL OR t.industry_id=:industry)")
    Page<Ticker> findAllByCountryAndSectorAndIndustry(@Param("country") Long country,
                                                      @Param("sector") Long sector,
                                                      @Param("industry") Long industry,
                                                      Pageable pageable);

}

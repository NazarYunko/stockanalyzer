package com.stockanalyzer.ticker.repository;

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
            "WHERE (:country IS NULL OR t.country=:country)" +
            "AND(:sector IS NULL OR t.sector=:sector)" +
            "AND(:industry IS NULL OR t.industry=:industry)",
            nativeQuery = true,
            countQuery = "SELECT count(*) FROM ticker t " +
                    "WHERE (:country IS NULL OR t.country=:country)" +
                    "AND(:sector IS NULL OR t.sector=:sector)" +
                    "AND(:industry IS NULL OR t.industry=:industry)")
    Page<Ticker> findAllByCountryAndSectorAndIndustry(@Param("country") String country,
                                                      @Param("sector") String sector,
                                                      @Param("industry") String industry,
                                                      Pageable pageable);

    @Query(value = "SELECT DISTINCT t.country FROM ticker t " +
            "WHERE t.country!=''", nativeQuery = true)
    Set<String> findAllCountries();

    @Query(value = "SELECT DISTINCT t.sector FROM ticker t " +
            "WHERE t.sector!=''", nativeQuery = true)
    Set<String> findAllSectors();

    @Query(value = "SELECT DISTINCT t.industry FROM ticker t " +
            "WHERE t.industry!= ''", nativeQuery = true)
    Set<String> findAllIndustries();
}

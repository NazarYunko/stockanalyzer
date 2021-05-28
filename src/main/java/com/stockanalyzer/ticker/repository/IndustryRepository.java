package com.stockanalyzer.ticker.repository;

import com.stockanalyzer.ticker.domain.Country;
import com.stockanalyzer.ticker.domain.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndustryRepository extends JpaRepository<Industry, Long> {

    Optional<Industry> findByName(String name);

}

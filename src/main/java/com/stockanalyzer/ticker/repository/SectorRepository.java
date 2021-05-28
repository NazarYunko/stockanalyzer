package com.stockanalyzer.ticker.repository;

import com.stockanalyzer.ticker.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    Optional<Sector> findByName(String name);

}

package dev.cheloti.populationdatams.repository;

import dev.cheloti.populationdatams.entities.County;

import java.util.List;
import java.util.Optional;

public interface CountyRepository {
    List<County> findCountiesPopulation();
    Optional<County> findCountyPopulationByCode(int code);
    Optional<County> findCountyPopulationByName(String name);
}

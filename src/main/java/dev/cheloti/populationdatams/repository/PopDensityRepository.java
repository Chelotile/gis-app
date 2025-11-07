package dev.cheloti.populationdatams.repository;

import dev.cheloti.populationdatams.domain.PopDensity;

import java.util.List;
import java.util.Optional;

public interface PopDensityRepository {
    List<PopDensity> findCountiesPopDensity();
    Optional<PopDensity> findCountyPopDensityByCode(int code);
    Optional<PopDensity> findCountyPopDensityByName(String name);
}

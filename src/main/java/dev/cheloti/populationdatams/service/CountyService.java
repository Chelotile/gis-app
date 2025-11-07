package dev.cheloti.populationdatams.service;


import dev.cheloti.populationdatams.dto.CountyDTO;

import java.util.List;
import java.util.Optional;

public interface CountyService {
    List<CountyDTO> getCountiesPopulation();
    Optional<CountyDTO> getCountyPopulationByCode(int code);
    Optional<CountyDTO> getCountyPopulationByName(String name);
}

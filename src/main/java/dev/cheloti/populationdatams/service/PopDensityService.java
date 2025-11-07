package dev.cheloti.populationdatams.service;

import dev.cheloti.populationdatams.dto.PopDensityDTO;

import java.util.List;
import java.util.Optional;

public interface PopDensityService {

    List<PopDensityDTO> getCountiesPopDensity();
    Optional<PopDensityDTO> getCountyPopDensityByCode(int code);
    Optional<PopDensityDTO> getCountyPopDensityByName(String name);
}

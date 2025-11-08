package dev.cheloti.populationdatams.service;

import dev.cheloti.populationdatams.dto.CountyDTO;


public interface CountyService {
    /**
     * GeoJSON FeatureCollection with Multi-feature for all counties
     */
    CountyDTO getCountiesPopulation();
    /**
     * GeoJSON FeatureCollection with single feature for specific county
     */
    CountyDTO getCountyPopulationByCode(int code);
    /**
     * GeoJSON FeatureCollection with single feature for specific county
     */
    CountyDTO getCountyPopulationByName(String name);
}

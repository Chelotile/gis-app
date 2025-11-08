package dev.cheloti.populationdatams.service;

import dev.cheloti.populationdatams.dto.PopDensityDTO;


public interface PopDensityService {

    /**
     * GeoJSON FeatureCollection with Multi-feature for all counties
     */
    PopDensityDTO getCountiesPopDensity();
    /**
     * GeoJSON FeatureCollection with single feature for specific county
     */
    PopDensityDTO getCountyPopDensityByCode(int code);
    /**
     * GeoJSON FeatureCollection with single feature for specific county
     */
    PopDensityDTO getCountyPopDensityByName(String name);
}

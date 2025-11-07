package dev.cheloti.populationdatams.dtoMapper;

import dev.cheloti.populationdatams.dto.CountyDTO;
import dev.cheloti.populationdatams.entities.County;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CountyMapper {

    public CountyDTO toCountyDTO(County county) {

        var feature = createFeature(county);
        CountyDTO countyDTO = new CountyDTO();
        countyDTO.setFeatures(List.of(feature));
        return countyDTO;

    }

    public CountyDTO toCountiesDTO(List<County> counties) {

        var features = counties.stream().map(this::createFeature).toList();

        var dto = new CountyDTO();
        dto.setFeatures(features);
        return dto;
    }

    private CountyDTO.Feature createFeature(County county) {

        var properties = new CountyDTO.Properties(

                county.getCode(),
                county.getName(),
                county.getPopulation()

        );

        var feature = new CountyDTO.Feature();
        feature.setProperties(properties);
        feature.setGeometry(county.getGeometry());
        return feature;
    }

}

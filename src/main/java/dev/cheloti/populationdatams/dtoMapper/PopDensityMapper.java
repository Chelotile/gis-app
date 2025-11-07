package dev.cheloti.populationdatams.dtoMapper;

import dev.cheloti.populationdatams.domain.PopDensity;
import dev.cheloti.populationdatams.dto.PopDensityDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PopDensityMapper {

    public PopDensityDTO toDTO(PopDensity popDensity) {
        var popDensityDTO = new PopDensityDTO();
        popDensityDTO.setFeatures(List.of(createFeature(popDensity)));
        return popDensityDTO;
    }

    public PopDensityDTO toDTOs(List<PopDensity> popDensityList) {
        var popDensityDTO = new PopDensityDTO();
        var feature = popDensityList.stream().map(this::createFeature).toList();
        popDensityDTO.setFeatures(feature);
        return popDensityDTO;
    }



    private PopDensityDTO.Feature createFeature(PopDensity popDensity) {
        var properties = new PopDensityDTO.Properties(
                popDensity.getCode(),
                popDensity.getName(),
                popDensity.getPopulationDensity()
        );

        var feature = new PopDensityDTO.Feature();

        feature.setProperties(properties);
        feature.setGeometry(popDensity.getGeometry());
        return feature;

    }
}

package dev.cheloti.populationdatams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.MultiPolygon;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountyDTO {

    private String type = "FeatureCollection";
    private List<Feature> features;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Feature {
        private String type = "Feature";
        private Properties properties;
        private MultiPolygon geometry;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Properties {
        private Integer code;
        private String  name;
        private Long   population;
    }

}

package dev.cheloti.populationdatams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.MultiPolygon;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PopDensityDTO {
private String type = "FeatureCollection";
private List<Feature> features;
@Data
@NoArgsConstructor
@AllArgsConstructor
public static class Feature{
    private String type = "Feature";
    private Properties properties;
    private MultiPolygon geometry;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public static class Properties{
    private Integer code;
    private String name;
    private BigDecimal populationDensity;
}
}

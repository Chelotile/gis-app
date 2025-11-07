package dev.cheloti.populationdatams.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
@NoArgsConstructor
@Getter
@Setter
public class County {
    private Long id;
    @NotNull(message = "county code is mandatory")
    @Positive(message = "county code must be positive")
    private Integer code;
    @NotBlank(message = "County name is mandatory")
    private String name;
    @NotNull(message = "population is mandatory")
    @PositiveOrZero(message = "population must be zero or positive")
    private Long population;
    @NotNull(message = "Geometry is mandatory")
    private MultiPolygon geometry;

    public County(Integer code, String name, Long population, MultiPolygon geometry) {
        this.code = code;
        this.name = name;
        this.population = population;
        this.geometry = geometry;
    }
}

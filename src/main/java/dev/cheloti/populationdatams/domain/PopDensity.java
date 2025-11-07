package dev.cheloti.populationdatams.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.locationtech.jts.geom.MultiPolygon;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PopDensity {
    @NotNull(message = "county code is mandatory")
    @Positive(message = "county code must be positive")
    private Integer code;
    @NotBlank(message = "County name is mandatory")
    private String name;
    @NotNull(message = "population density is mandatory")
    @PositiveOrZero(message = "population density must be zero or positive")
    private BigDecimal populationDensity;
    @NotNull(message = "Geometry is mandatory")
    private MultiPolygon geometry;

}

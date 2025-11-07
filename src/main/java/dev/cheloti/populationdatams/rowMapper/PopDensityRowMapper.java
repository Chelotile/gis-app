package dev.cheloti.populationdatams.rowMapper;

import dev.cheloti.populationdatams.domain.PopDensity;
import dev.cheloti.populationdatams.utilis.PropertyReader;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class PopDensityRowMapper implements RowMapper<PopDensity> {

    private final PropertyReader reader;
    @Override
    public PopDensity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PopDensity(
                rs.getInt("county_code"),
                rs.getString("county_name"),
                rs.getBigDecimal("population_density"),
                reader.parseGeoJSON(rs.getString("geometry"))
        );
    }
}

package dev.cheloti.populationdatams.rowMapper;

import dev.cheloti.populationdatams.entities.County;
import dev.cheloti.populationdatams.utilis.Reader;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class CountyRowMapper implements RowMapper<County> {
    private final Reader reader;
    @Override
    public County mapRow(ResultSet rs, int rowNum) throws SQLException {
        return  new County(
                rs.getInt("county_code"),
                rs.getString("county_name"),
                rs.getLong("population"),
                reader.parseGeoJSON(rs.getString("geometry"))
        );
    }
}

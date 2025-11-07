package dev.cheloti.populationdatams.repository.impl;

import dev.cheloti.populationdatams.domain.PopDensity;
import dev.cheloti.populationdatams.repository.PopDensityRepository;
import dev.cheloti.populationdatams.rowMapper.PopDensityRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
@Slf4j
public class PopDensityRepositoryImpl implements PopDensityRepository {

    private final PopDensityRowMapper mapper;
    private final JdbcClient jdbc;

    @Override
    public List<PopDensity> findCountiesPopDensity() {
        try {
            var sqlquery = """
                    SELECT c.county_code,
                    c.county_name,
                    round(c.population/(st_area(st_transform(c.geometry, 21097))/1000000)) as population_density,
                    st_asgeojson(st_simplify(c.geometry::geometry, 0.001)) as geometry
                    FROM counties c
                    ORDER BY c.county_code 
                    """;
            return jdbc.sql(sqlquery)
                    .query(mapper)
                    .list();
        } catch (BadSqlGrammarException e) {
            log.error("error occurred while fetching counties pop density", e);
            log.error(e.getSql());
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PopDensity> findCountyPopDensityByCode(int code) {
        var sql = """
                SELECT c.county_code,
                c.county_name,
                ceil(c.population/(st_area(st_transform(c.geometry, 21097))/1000000)) as population_density,
                st_asgeojson(st_simplify(c.geometry::geometry, 0.001)) as geometry
                FROM counties c
                WHERE c.county_code = ?
                """;
        return jdbc.sql(sql)
                .param(code)
                .query(mapper)
                .optional();
    }

    @Override
    public Optional<PopDensity> findCountyPopDensityByName(String name) {
        var sql = """
                SELECT c.county_code,
                c.county_name,
                ceil(c.population/(st_area(st_transform(c.geometry, 21097))/1000000)) as population_density,
                st_asgeojson(st_simplify(c.geometry::geometry, 0.001)) as geometry
                FROM counties c
                WHERE c.county_name ILIKE $1
                """;
        return jdbc.sql(sql)
                .param(name)
                .query(mapper)
                .optional();
    }
}

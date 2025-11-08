package dev.cheloti.populationdatams.repository.impl;

import dev.cheloti.populationdatams.domain.PopDensity;
import dev.cheloti.populationdatams.queries.PopDensityQueries;
import dev.cheloti.populationdatams.repository.PopDensityRepository;
import dev.cheloti.populationdatams.repository.dataBaseClient.JDBClient;
import dev.cheloti.populationdatams.rowMapper.PopDensityRowMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
@Slf4j
public class PopDensityRepositoryImpl implements PopDensityRepository {

    private final PopDensityRowMapper mapper;
    private final JDBClient jdbc;

    @Override
    public List<PopDensity> findCountiesPopDensity() {
        try {
            var query = PopDensityQueries.GET_ALL_COUNTIES_POP_DENSITY;
            return jdbc.getAllQuery(query, mapper);

        } catch (BadSqlGrammarException e) {
            log.error("error occurred while fetching counties pop density", e);
            log.error(e.getSql());
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PopDensity> findCountyPopDensityByCode(@NotNull int code) {
        var sql = PopDensityQueries.GET_COUNTY_POP_DENSITY_BY_CODE;
        return jdbc.getQuery(sql, mapper, code);
    }

    @Override
    public Optional<PopDensity> findCountyPopDensityByName(@NotNull String name) {
        var sql = PopDensityQueries.GET_COUNTY_POP_DENSITY_NAME;
        return jdbc.getQuery(sql, mapper, name);
    }
}

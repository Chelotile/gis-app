package dev.cheloti.populationdatams.repository.impl;

import dev.cheloti.populationdatams.domain.PopDensity;
import dev.cheloti.populationdatams.exceptions.SQLQueryException;
import dev.cheloti.populationdatams.queries.PopDensityQueries;
import dev.cheloti.populationdatams.repository.PopDensityRepository;
import dev.cheloti.populationdatams.repository.dataBaseClient.JDBClient;
import dev.cheloti.populationdatams.rowMapper.PopDensityRowMapper;
import dev.cheloti.populationdatams.validation.Validator;
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
    private final Validator validator;
    @Override
    public List<PopDensity> findCountiesPopDensity() {
        try {
            var query = PopDensityQueries.GET_ALL_COUNTIES_POP_DENSITY;
            return jdbc.getAllQuery(query, mapper);

        } catch (BadSqlGrammarException e) {
            log.error("Error fetching counties pop density. SQL: {}", e.getSql());
            throw new SQLQueryException("Error fetching counties pop density");
        }
    }

    @Override
    public List<PopDensity> findCountiesWithPopDensityAbove(long popDensityAbove) {

        try {
            var query = PopDensityQueries.GET_COUNTIES_WITH_POP_DENSITY_ABOVE;
            return jdbc.getAllQuery(query,mapper, popDensityAbove);
        } catch (BadSqlGrammarException e) {
            log.error("SQL: {}", e.getSQLException());
            throw new SQLQueryException("Error fetching counties with pop density above.");
        }

    }

    @Override
    public Optional<PopDensity> findCountyPopDensityByCode(@NotNull int code) {
        validator.validateCode(code);
        try {
            var sql = PopDensityQueries.GET_COUNTY_POP_DENSITY_BY_CODE;
            return jdbc.getQuery(sql, mapper, code);
        } catch (BadSqlGrammarException e) {
            log.error("Error fetching county pop density by code. SQL: {}", e.getSql());
            throw new SQLQueryException("Error fetching county pop density by code");
        }
    }

    @Override
    public Optional<PopDensity> findCountyPopDensityByName(@NotNull String name) {
        validator.validateName(name);
        try {
            var sql = PopDensityQueries.GET_COUNTY_POP_DENSITY_BY_NAME;
            return jdbc.getQuery(sql, mapper, name);
        } catch (BadSqlGrammarException e) {
            log.error("Error fetching county pop density by name. SQL: {}", e.getSql());
            throw new SQLQueryException("Error fetching county pop density by name");
        }
    }
}

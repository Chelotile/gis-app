package dev.cheloti.populationdatams.repository.impl;

import dev.cheloti.populationdatams.entities.County;
import dev.cheloti.populationdatams.exceptions.SQLQueryException;
import dev.cheloti.populationdatams.queries.CountyQueries;
import dev.cheloti.populationdatams.repository.CountyRepository;
import dev.cheloti.populationdatams.repository.dataBaseClient.JDBClient;
import dev.cheloti.populationdatams.rowMapper.CountyRowMapper;
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
public class CountyRepositoryImpl implements CountyRepository {

    private final JDBClient jdbc;
    private final CountyRowMapper countyRowMapper;
    private final Validator validator;

    @Override
    public List<County> findCountiesPopulation() {
        try {
            String sql = CountyQueries.GET_COUNTIES_POPULATION;
            return jdbc.getAllQuery(sql, countyRowMapper);

        } catch (BadSqlGrammarException e) {
            log.error("Error fetching counties population. SQl: {}", e.getSql());
            throw new SQLQueryException("Error fetching counties population");
        }
    }

    @Override
    public List<County> findCountiesWithPopAbove(long minPopulation) {

        try {
            var sql = CountyQueries.GET_COUNTIES_WITH_POPULATION_ABOVE;
            return jdbc.getAllQuery(sql, countyRowMapper, minPopulation);
        } catch (BadSqlGrammarException e) {
            log.error("Error fetching counties population above. SQl: {}", e.getSql());
            throw new SQLQueryException(String.format("Error fetching counties with population above %d ", minPopulation));
        }
    }

    @Override
    public Optional<County> findCountyPopulationByCode(@NotNull int code) {
        validator.validateCode(code);
        try {
            String sql = CountyQueries.GET_COUNTY_POPULATION_BY_CODE;
            return jdbc.getQuery(sql, countyRowMapper, code);
        } catch (BadSqlGrammarException e) {
            log.error(e.getSql());
            throw new SQLQueryException(String.format("Error fetching population county no %d ", code));
        }
    }

    @Override
    public Optional<County> findCountyPopulationByName(@NotNull String name) {
        validator.validateName(name);
        try {
            var sql = CountyQueries.GET_COUNTY_POPULATION_BY_NAME;
            return jdbc.getQuery(sql, countyRowMapper, name);
        } catch (BadSqlGrammarException e) {
            log.error(e.getSql());
            throw new SQLQueryException(String.format("Error fetching population for %s county", name));
        }
    }
}

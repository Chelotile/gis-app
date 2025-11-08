package dev.cheloti.populationdatams.repository.impl;

import dev.cheloti.populationdatams.entities.County;
import dev.cheloti.populationdatams.queries.CountyQueries;
import dev.cheloti.populationdatams.repository.CountyRepository;
import dev.cheloti.populationdatams.repository.dataBaseClient.JDBClient;
import dev.cheloti.populationdatams.rowMapper.CountyRowMapper;
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
public class CountyRepositoryImpl implements CountyRepository {

    private final JDBClient jdbc;
    private final CountyRowMapper countyRowMapper;

    @Override
    public List<County> findCountiesPopulation() {
        try {
            String sql = CountyQueries.GET_COUNTIES_POPULATION;
            return jdbc.getAllQuery(sql, countyRowMapper);

        } catch (BadSqlGrammarException e) {
            log.error( e.getSql());
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<County> findCountyPopulationByCode(int code) {
        try {
            String sql = CountyQueries.GET_COUNTY_POPULATION_BY_CODE;
            return jdbc.getQuery(sql, countyRowMapper, code);
        } catch (BadSqlGrammarException e) {
            log.error( e.getSql());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<County> findCountyPopulationByName(String name) {

        try {
            var sql = CountyQueries.GET_COUNTY_POPULATION_BY_NAME;
            return jdbc.getQuery(sql, countyRowMapper, name);
        } catch (BadSqlGrammarException e) {
            log.error(e.getMessage());
            log.error(e.getSql());
            throw new RuntimeException(e);
        }
    }
}

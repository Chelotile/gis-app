package dev.cheloti.populationdatams.repository.dataBaseClient;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class JDBClient implements DatabaseClient {

    private final JdbcClient jdbcClient;

    @Override
    public <T> List<T> getAllQuery(String sql, RowMapper<T> rowMapper, Object... params) {
        var statement = jdbcClient.sql(sql);
        for (Object param : params) {
            statement = statement.params(param);
        }
        return statement.query(rowMapper).list();
    }

    @Override
    public <T> Optional<T> getQuery(String sql, RowMapper<T> rowMapper, Object... params) {
        var statement = jdbcClient.sql(sql);
        for (Object param : params) {
            statement = statement.params(param);
        }
        return statement.query(rowMapper).optional();
    }
}

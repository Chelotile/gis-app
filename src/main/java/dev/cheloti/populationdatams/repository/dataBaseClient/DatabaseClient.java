package dev.cheloti.populationdatams.repository.dataBaseClient;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public interface DatabaseClient {
    <T> List<T> getAllQuery(String sql, RowMapper<T> rowMapper, Object... params);
    <T> Optional<T> getQuery(String sql, RowMapper<T> rowMapper, Object... params);
}

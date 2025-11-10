package dev.cheloti.populationdatams.exceptions;

import dev.cheloti.populationdatams.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Map;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleResourceNotFound(ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new Response(
                                ex.getMessage(),
                                HttpStatus.NOT_FOUND,
                                HttpStatus.NOT_FOUND.value(),
                                Map.of()
                        )
                );
    }
    @ExceptionHandler(SQLQueryException.class)
    public ResponseEntity<Response> handleSQlException(SQLQueryException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        new Response(
                                ex.getMessage(),
                                HttpStatus.CONFLICT,
                                HttpStatus.CONFLICT.value(),
                                Map.of()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneralException(Exception ex) {
        log.error("Unexpected error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new Response(
                                ex.getMessage(),
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                Map.of()
                        )
                );
    }
}

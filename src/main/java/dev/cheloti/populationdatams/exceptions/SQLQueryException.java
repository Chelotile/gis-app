package dev.cheloti.populationdatams.exceptions;

public class SQLQueryException extends RuntimeException {
    public SQLQueryException(String message) {
        super(message);
    }
}

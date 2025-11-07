package dev.cheloti.populationdatams.domain;


import org.springframework.http.HttpStatus;

import java.util.Map;

public record Response(
        String message,
        HttpStatus status,
        Integer code,
        Map<?,?> data
) {
}

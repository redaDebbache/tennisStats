package com.debbache.tennisstats.infra.rest;

import com.debbache.tennisstats.domain.exception.MissingPlayersStatisticsException;
import com.debbache.tennisstats.domain.exception.PlayerNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class ExceptionManager {
    @ExceptionHandler(value = {PlayerNotFoundException.class})
    public ResponseEntity<ApiError> notFound(RuntimeException exception) {
        log.error("Handling exception for ", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(value = {MissingPlayersStatisticsException.class})
    public ResponseEntity<ApiError> notImplemented(RuntimeException exception) {
        log.error("Handling exception for ", exception);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ApiError(HttpStatus.SERVICE_UNAVAILABLE.value(), exception.getMessage()));
    }

}

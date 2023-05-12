package com.debbache.tennisstats.domain.exception;

public class MissingPlayersStatisticsException extends RuntimeException {
    public MissingPlayersStatisticsException() {
        super("Required data to compute statistics is missing.");
    }
}

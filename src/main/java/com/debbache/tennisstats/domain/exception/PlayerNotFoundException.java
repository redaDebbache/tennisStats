package com.debbache.tennisstats.domain.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long id) {
        super(String.format("Player having id %d not found.", id));
    }
}

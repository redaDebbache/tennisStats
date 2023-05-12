package com.debbache.tennisstats.infra.rest;

public record ApiError(Integer status, String message) {
}

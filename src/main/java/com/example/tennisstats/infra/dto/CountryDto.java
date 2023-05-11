package com.example.tennisstats.infra.dto;

import lombok.extern.jackson.Jacksonized;

@Jacksonized
public record CountryDto(String picture, String code) {
}

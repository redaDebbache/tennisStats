package com.example.tennisstats.domain.model;

import lombok.extern.jackson.Jacksonized;

@Jacksonized
public record CountryModel(String picture, String code) {
}

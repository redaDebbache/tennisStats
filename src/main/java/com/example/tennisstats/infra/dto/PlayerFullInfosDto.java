package com.example.tennisstats.infra.dto;


public record PlayerFullInfosDto(Long id, String firstName, String lastName, String shortName, String picture, String sex,
                                 CountryDto country, PlayerDataDto data) {
}

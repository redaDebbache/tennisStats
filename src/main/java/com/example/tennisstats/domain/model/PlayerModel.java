package com.example.tennisstats.domain.model;

public record PlayerModel(Long id, String firstName, String lastName, String shortName, String picture, String sex,
                          CountryModel country, PlayerDataModel data) {
}

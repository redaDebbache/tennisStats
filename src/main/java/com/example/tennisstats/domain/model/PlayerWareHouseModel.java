package com.example.tennisstats.domain.model;

import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
public record PlayerWareHouseModel(List<PlayerModel> players) {
}

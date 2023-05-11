package com.example.tennisstats.domain.model;

import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
public record PlayerDataModel(Integer rank, Long points, long weight, Integer height, Integer age, List<Integer> last) {
}

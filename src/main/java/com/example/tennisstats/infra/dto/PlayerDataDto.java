package com.example.tennisstats.infra.dto;

import java.util.List;

public record PlayerDataDto(Integer rank, Long points, long weight, Integer height, Integer age, List<Integer> last) {}

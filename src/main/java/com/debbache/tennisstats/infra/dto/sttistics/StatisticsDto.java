package com.debbache.tennisstats.infra.dto.sttistics;

public record StatisticsDto(CountryWinLosesStatisticDto winnerCountry, Float averageBmi, Integer heightMedian) {
}

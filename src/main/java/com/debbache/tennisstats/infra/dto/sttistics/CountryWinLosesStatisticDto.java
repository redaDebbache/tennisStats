package com.debbache.tennisstats.infra.dto.sttistics;

import com.debbache.tennisstats.domain.model.player.CountryModel;

public record CountryWinLosesStatisticDto(CountryModel country, Integer wins, Integer loses, Float ratio) {
}

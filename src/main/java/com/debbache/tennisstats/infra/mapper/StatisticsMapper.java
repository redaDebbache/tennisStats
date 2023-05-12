package com.debbache.tennisstats.infra.mapper;

import com.debbache.tennisstats.domain.model.player.CountryModel;
import com.debbache.tennisstats.domain.model.statistics.StatisticsModel;
import com.debbache.tennisstats.domain.model.statistics.WinLosesStatistic;
import com.debbache.tennisstats.domain.model.statistics.WinningCountryRatioStatistic;
import com.debbache.tennisstats.infra.dto.player.CountryDto;
import com.debbache.tennisstats.infra.dto.sttistics.CountryWinLosesStatisticDto;
import com.debbache.tennisstats.infra.dto.sttistics.StatisticsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {

    @Mapping(target = "averageBmi", expression = "java(source.bmiPlayerStatistic().getAverageBmi())")
    @Mapping(target = "heightMedian", expression = "java(source.playersHeightMedian().getHeightMedian())")
    @Mapping(target = "winnerCountry", source = "mostWinningCountry")
    StatisticsDto map(StatisticsModel source);

    @Mapping(target = "country", expression = "java(source.getCountry())")
    @Mapping(target = "wins", expression = "java(source.getWins())")
    @Mapping(target = "loses", expression = "java(source.getLoses())")
    @Mapping(target = "ratio", expression = "java(source.getRatio())")
    CountryWinLosesStatisticDto map(WinLosesStatistic source);

    default CountryWinLosesStatisticDto map(WinningCountryRatioStatistic source) {
        return Optional.ofNullable(source).map(w -> map(w.getWinnerCountry())).orElse(null);
    }

    CountryDto map(CountryModel source);
}

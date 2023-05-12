package com.debbache.tennisstats.infra.mapper;

import com.debbache.tennisstats.domain.model.statistics.StatisticsModel;
import com.debbache.tennisstats.domain.model.statistics.WinLosesStatistic;
import com.debbache.tennisstats.domain.model.statistics.WinningCountryRatioStatistic;
import com.debbache.tennisstats.infra.dto.sttistics.CountryWinLosesStatisticDto;
import com.debbache.tennisstats.infra.dto.sttistics.StatisticsDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.debbache.tennisstats.utils.StatisticsStubUtils.stubStatisticsModel;

class StatisticsMapperTest {
    private final StatisticsMapper mapper = new StatisticsMapperImpl();

    @Test
    public void should_map_StatisticsModel_to_CountryWinLosesStatisticDto() {
        //Given
        StatisticsModel source = stubStatisticsModel();
        //When
        StatisticsDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNotNull();

        Assertions.assertThat(target.winnerCountry()).isNotNull();
        Assertions.assertThat(target.winnerCountry().wins()).isEqualTo(source.mostWinningCountry().getWinnerCountry().getWins());
        Assertions.assertThat(target.winnerCountry().loses()).isEqualTo(source.mostWinningCountry().getWinnerCountry().getLoses());
        Assertions.assertThat(target.winnerCountry().ratio()).isEqualTo(source.mostWinningCountry().getWinnerCountry().getRatio());
        Assertions.assertThat(target.winnerCountry().country()).isNotNull();
        Assertions.assertThat(target.winnerCountry().country().code()).isEqualTo(source.mostWinningCountry().getWinnerCountry().getCountry().code());
        Assertions.assertThat(target.winnerCountry().country().picture()).isEqualTo(source.mostWinningCountry().getWinnerCountry().getCountry().picture());

        Assertions.assertThat(target.heightMedian()).isEqualTo(source.playersHeightMedian().getHeightMedian());

        Assertions.assertThat(target.averageBmi()).isEqualTo(source.bmiPlayerStatistic().getAverageBmi());
    }

    @Test
    public void should_return_null_when_StatisticsModel_is_null() {
        //Given
        StatisticsModel source = null;
        //When
        StatisticsDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNull();
    }

    @Test
    public void should_map_WinLosesStatistic_to_CountryWinLosesStatisticDto() {
        //Given
        StatisticsModel statisticsModel = stubStatisticsModel();
        WinningCountryRatioStatistic source = statisticsModel.mostWinningCountry();
        //When
        CountryWinLosesStatisticDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNotNull();

        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(target.wins()).isEqualTo(source.getWinnerCountry().getWins());
        Assertions.assertThat(target.loses()).isEqualTo(source.getWinnerCountry().getLoses());
        Assertions.assertThat(target.country()).isNotNull();
        Assertions.assertThat(target.country().code()).isEqualTo(source.getWinnerCountry().getCountry().code());
        Assertions.assertThat(target.country().picture()).isEqualTo(source.getWinnerCountry().getCountry().picture());
    }

    @Test
    public void should_return_null_when_WinLosesStatistic_is_null() {
        //Given
        WinningCountryRatioStatistic source = null;
        //When
        CountryWinLosesStatisticDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNull();
    }


    @Test
    public void should_map_WinningCountryRatioStatistic_to_CountryWinLosesStatisticDto() {
        //Given
        StatisticsModel statisticsModel = stubStatisticsModel();
        WinLosesStatistic source = statisticsModel.mostWinningCountry().getWinnerCountry();
        //When
        CountryWinLosesStatisticDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNotNull();

        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(target.ratio()).isEqualTo(source.getRatio());
        Assertions.assertThat(target.wins()).isEqualTo(source.getWins());
        Assertions.assertThat(target.loses()).isEqualTo(source.getLoses());
        Assertions.assertThat(target.country()).isNotNull();
        Assertions.assertThat(target.country().code()).isEqualTo(source.getCountry().code());
        Assertions.assertThat(target.country().picture()).isEqualTo(source.getCountry().picture());
    }

    @Test
    public void should_return_null_when_CountryWinLosesStatisticDto_is_null() {
        //Given
        WinLosesStatistic source = null;
        //When
        CountryWinLosesStatisticDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNull();
    }
}

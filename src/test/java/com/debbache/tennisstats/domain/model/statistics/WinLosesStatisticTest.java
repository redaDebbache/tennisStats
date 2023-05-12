package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.CountryModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class WinLosesStatisticTest {

    @Test
    public void should_compute_wins_count_and_loses_count_from_results() {
        //Given
        CountryModel country = new CountryModel("picture", "ESP");
        List<Integer> allGameResults = List.of(1, 0, 0, 1, 1);
        //When
        WinLosesStatistic winLosesStatistic = new WinLosesStatistic(country, allGameResults);
        //Then
        Assertions.assertThat(winLosesStatistic.getCountry()).isEqualTo(country);
        Assertions.assertThat(winLosesStatistic.getWins()).isEqualTo(3);
        Assertions.assertThat(winLosesStatistic.getLoses()).isEqualTo(2);
        Assertions.assertThat(winLosesStatistic.getRatio()).isEqualTo(60);
    }

    @Test
    public void statistics_should_be_kept_as_init_when_allGameResults_is_empty() {
        //Given
        CountryModel country = new CountryModel("picture", "ESP");
        List<Integer> allGameResults = Collections.emptyList();
        //When
        WinLosesStatistic winLosesStatistic = new WinLosesStatistic(country, allGameResults);
        //Then
        Assertions.assertThat(winLosesStatistic.getCountry()).isNull();
        Assertions.assertThat(winLosesStatistic.getWins()).isNull();
        Assertions.assertThat(winLosesStatistic.getLoses()).isNull();
        Assertions.assertThat(winLosesStatistic.getRatio()).isNull();
    }
}

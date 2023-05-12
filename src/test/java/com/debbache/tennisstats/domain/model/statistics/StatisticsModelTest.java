package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.PlayerModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.debbache.tennisstats.utils.PlayersStubUtils.stubPlayers;

class StatisticsModelTest {
    @Test
    public void should_return_optional_empty_when_players_list_is_null() {
        //Given
        List<PlayerModel> players = null;
        //When
        Optional<StatisticsModel> statisticsModel = StatisticsModel.of(players);
        //Then
        Assertions.assertThat(statisticsModel).isEmpty();
    }

    @Test
    public void should_return_optional_empty_when_players_list_is_empty() {
        //Given
        List<PlayerModel> players = Collections.emptyList();
        //When
        Optional<StatisticsModel> statisticsModel = StatisticsModel.of(players);
        //Then
        Assertions.assertThat(statisticsModel).isEmpty();
    }

    @Test
    public void should_return_optional_with_statistics_model_instance_when_players_list_is_valid() {
        //Given
        List<PlayerModel> players = stubPlayers();
        //When
        Optional<StatisticsModel> statisticsModel = StatisticsModel.of(players);
        //Then
        Assertions.assertThat(statisticsModel).isNotEmpty();

        StatisticsModel instance = statisticsModel.get();
        Assertions.assertThat(instance.bmiPlayerStatistic()).isNotNull();
        Assertions.assertThat(instance.mostWinningCountry().getWinnerCountry()).isNotNull();
        Assertions.assertThat(instance.playersHeightMedian()).isNotNull();
    }
}

package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.PlayerModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.debbache.tennisstats.utils.PlayersStubUtils.stubPlayers;

class BmiPlayerStatisticTest {

    @Test
    public void should_calculate_players_bmi_average() {
        //Given
        List<PlayerModel> playerModels = stubPlayers();
        //When
        BmiPlayerStatistic bmiPlayerStatistic = new BmiPlayerStatistic(playerModels);
        //Then
        Assertions.assertThat(bmiPlayerStatistic.getAverageBmi()).isEqualTo(23.3f);
    }

    @Test
    public void should_not_calculate_players_bmi_average_when_players_list_is_null() {
        //Given
        List<PlayerModel> playerModels = null;
        //When
        BmiPlayerStatistic bmiPlayerStatistic = new BmiPlayerStatistic(playerModels);
        //Then
        Assertions.assertThat(bmiPlayerStatistic.getAverageBmi()).isNull();
    }

    @Test
    public void should_not_calculate_players_bmi_average_when_players_list_is_empty() {
        //Given
        List<PlayerModel> playerModels = null;
        //When
        BmiPlayerStatistic bmiPlayerStatistic = new BmiPlayerStatistic(playerModels);
        //Then
        Assertions.assertThat(bmiPlayerStatistic.getAverageBmi()).isNull();
    }

}

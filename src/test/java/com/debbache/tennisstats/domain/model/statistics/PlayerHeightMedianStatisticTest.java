package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.PlayerDataModel;
import com.debbache.tennisstats.domain.model.player.PlayerModel;
import com.debbache.tennisstats.utils.PlayersStubUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class PlayerHeightMedianStatisticTest {

    @Test
    public void should_keep_0_when_players_list_is_empty() {
        //Given
        List<PlayerModel> playerModels = Collections.emptyList();
        //When
        PlayerHeightMedianStatistic playerHeightMedianStatistic = new PlayerHeightMedianStatistic(playerModels);
        //Then
        Assertions.assertThat(playerHeightMedianStatistic.getHeightMedian()).isNull();
    }

    @Test
    public void should_keep_0_when_players_list_is_null() {
        //Given
        List<PlayerModel> playerModels = null;
        //When
        PlayerHeightMedianStatistic playerHeightMedianStatistic = new PlayerHeightMedianStatistic(playerModels);
        //Then
        Assertions.assertThat(playerHeightMedianStatistic.getHeightMedian()).isNull();
    }

    @Test
    public void should_calculate_median_when_players_list_is_valid() {
        //Given
        List<PlayerModel> playerModels = PlayersStubUtils.stubPlayers();
        //When
        PlayerHeightMedianStatistic playerHeightMedianStatistic = new PlayerHeightMedianStatistic(playerModels);
        //Then
        Assertions.assertThat(playerHeightMedianStatistic.getHeightMedian()).isEqualTo(185);
    }

    @Test
    public void should_take_into_calculation_only_players_having_height() {
        //Given
        List<PlayerModel> playerModels = PlayersStubUtils.stubPlayers();
        PlayerDataModel playerDataModel = new PlayerDataModel(0, 0L, 0, 0, 0, null);
        PlayerModel nullHeightPlayerModel = new PlayerModel(1L, "", "", "", "", "null", null, playerDataModel);
        playerModels.add(nullHeightPlayerModel);
        //When
        PlayerHeightMedianStatistic playerHeightMedianStatistic = new PlayerHeightMedianStatistic(playerModels);
        //Then
        Assertions.assertThat(playerHeightMedianStatistic.getHeightMedian()).isEqualTo(185);
    }
}

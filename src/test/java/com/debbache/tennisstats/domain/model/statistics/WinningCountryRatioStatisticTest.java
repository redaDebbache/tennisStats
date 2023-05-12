package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.PlayerModel;
import com.debbache.tennisstats.utils.PlayersStubUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class WinningCountryRatioStatisticTest {

    @Test
    public void should_init_with_the_winner_country() {
        //Given
        List<PlayerModel> playerModels = PlayersStubUtils.stubPlayers();
        //When
        WinningCountryRatioStatistic winningCountryRatioStatistic = new WinningCountryRatioStatistic(playerModels);
        //Then
        WinLosesStatistic winnerCountry = winningCountryRatioStatistic.getWinnerCountry();
        Assertions.assertThat(winnerCountry).isNotNull();
        Assertions.assertThat(winnerCountry.getCountry().code()).isEqualTo("SRB");
    }

    @Test
    public void null_player_not_taken_into_account_in_calculation() {
        //Given
        List<PlayerModel> playerModels = PlayersStubUtils.stubPlayers();
        playerModels.add(null);
        //When
        WinningCountryRatioStatistic winningCountryRatioStatistic = new WinningCountryRatioStatistic(playerModels);
        //Then
        WinLosesStatistic winnerCountry = winningCountryRatioStatistic.getWinnerCountry();
        Assertions.assertThat(winnerCountry).isNotNull();
        Assertions.assertThat(winnerCountry.getCountry().code()).isEqualTo("SRB");
    }
}

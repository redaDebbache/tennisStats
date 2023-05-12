package com.debbache.tennisstats.domain.model.player;

import com.debbache.tennisstats.utils.PlayersStubUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class PlayerModelTest {
    @Test
    public void lastGameResults_should_return_last_game_results_for_player() {
        //Given
        PlayerModel playerModel = PlayersStubUtils.stubPlayers().get(0);
        //When
        List<Integer> lastGameResults = playerModel.lastGameResults();
        //Then
        Assertions.assertThat(lastGameResults).isNotEmpty().isEqualTo(playerModel.data().last());
    }

    @Test
    public void lastGameResults_should_return_empty_list_when_data_is_null() {
        //Given
        PlayerModel playerModel = new PlayerModel(1L, "", "", "", "", "null", null, null);
        //When
        List<Integer> lastGameResults = playerModel.lastGameResults();
        //Then
        Assertions.assertThat(lastGameResults).isEmpty();
    }

    @Test
    public void lastGameResults_should_return_empty_list_when_data_is_not_null_but_last_is_null() {
        //Given
        PlayerDataModel playerDataModel = new PlayerDataModel(0, 0L, 0, 0, 0, null);
        PlayerModel playerModel = new PlayerModel(1L, "", "", "", "", "null", null, playerDataModel);
        //When
        List<Integer> lastGameResults = playerModel.lastGameResults();
        //Then
        Assertions.assertThat(lastGameResults).isEmpty();
    }

    @Test
    public void getWeight_should_return_last_game_results_for_player() {
        //Given
        PlayerModel playerModel = PlayersStubUtils.stubPlayers().get(0);
        //When
        Optional<Integer> weight = playerModel.getWeight();
        //Then
        Assertions.assertThat(weight).isNotEmpty().contains(playerModel.data().weight());
    }


    @Test
    public void getWeight_should_return_optional_empty_when_data_is_null() {
        //Given
        PlayerModel playerModel = new PlayerModel(1L, "", "", "", "", "null", null, null);
        //When
        Optional<Integer> weight = playerModel.getWeight();
        //Then
        Assertions.assertThat(weight).isEmpty();
    }

    @Test
    public void getWeight_should_return_optional_empty_when_data_is_not_null_but_weight_is_le_zero() {
        //Given
        PlayerDataModel playerDataModel = new PlayerDataModel(0, 0L, 0, 0, 0, null);
        PlayerModel playerModel = new PlayerModel(1L, "", "", "", "", "null", null, playerDataModel);
        //When
        Optional<Integer> weight = playerModel.getWeight();
        //Then
        Assertions.assertThat(weight).isEmpty();
    }

    @Test
    public void getHeight_should_return_last_game_results_for_player() {
        //Given
        PlayerModel playerModel = PlayersStubUtils.stubPlayers().get(0);
        //When
        Optional<Integer> height = playerModel.getHeight();
        //Then
        Assertions.assertThat(height).isNotEmpty().contains(playerModel.data().height());
    }


    @Test
    public void getHeight_should_return_null_when_data_is_null() {
        //Given
        PlayerModel playerModel = new PlayerModel(1L, "", "", "", "", "null", null, null);
        //When
        Optional<Integer> height = playerModel.getHeight();
        //Then
        Assertions.assertThat(height).isEmpty();
    }

    @Test
    public void getHeight_should_return_null_when_data_is_not_null_but_weight_is_le_zero() {
        //Given
        PlayerDataModel playerDataModel = new PlayerDataModel(0, 0L, 0, 0, 0, null);
        PlayerModel playerModel = new PlayerModel(1L, "", "", "", "", "null", null, playerDataModel);
        //When
        Optional<Integer> height = playerModel.getHeight();
        //Then
        Assertions.assertThat(height).isEmpty();
    }

    @Test
    public void getBmi_should_return_valid_value_when_heigh_and_wheigh_are_available() {
        //Given
        PlayerModel playerModel = PlayersStubUtils.stubPlayers().get(0);
        //When
        Optional<Float> bmi = playerModel.getBmi();
        //Then
        Assertions.assertThat(bmi).isPresent().contains(22.6f);
    }

    @Test
    public void getBmi_should_return_optional_empty_when_height_is_missing() {
        //Given
        PlayerDataModel playerDataModel = new PlayerDataModel(0, 0L, 80000, 0, 0, null);
        PlayerModel playerModel = new PlayerModel(1L, "", "", "", "", "null", null, playerDataModel);
        //When
        Optional<Float> bmi = playerModel.getBmi();
        //Then
        Assertions.assertThat(bmi).isEmpty();
    }

    @Test
    public void getBmi_should_return_optional_empty_when_weight_is_missing() {
        //Given
        PlayerDataModel playerDataModel = new PlayerDataModel(0, 0L, 0, 188, 0, null);
        PlayerModel playerModel = new PlayerModel(1L, "", "", "", "", "null", null, playerDataModel);
        //When
        Optional<Float> bmi = playerModel.getBmi();
        //Then
        Assertions.assertThat(bmi).isEmpty();
    }


}

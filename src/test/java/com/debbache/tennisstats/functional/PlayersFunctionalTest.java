package com.debbache.tennisstats.functional;

import com.debbache.tennisstats.infra.dto.player.PlayerDto;
import com.debbache.tennisstats.infra.dto.player.PlayerFullInfosDto;
import com.debbache.tennisstats.infra.dto.player.PlayersResponse;
import com.debbache.tennisstats.utils.PlayersStubUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Comparator;

public class PlayersFunctionalTest extends FunctionalTest {
    private static final String API_URL = "/api/v1/players";

    @Test
    public void user_requests_all_player_and_get_SUCCESS_and_valid_result() throws Exception {
        //When
        MockHttpServletResponse response = performCall(API_URL);

        //Then
        PlayersResponse playersResponse = objectMapper.readValue(response.getContentAsString(), PlayersResponse.class);
        Assertions.assertThat(playersResponse).isNotNull();
        Assertions.assertThat(playersResponse.players()).isNotNull()
                .hasSize(PlayersStubUtils.stubPlayerDtos().size())
                .doesNotContainNull()
                .isSortedAccordingTo(Comparator.comparing(PlayerDto::points));

        //And
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void user_requests_a_single_player_with_a_valid_id_and_get_SUCCESS_and_valid_result() throws Exception {
        //Given
        Long playerId = 52L;

        //When
        MockHttpServletResponse response = performCall(String.format("%s/%d", API_URL, playerId));

        //Then
        PlayerFullInfosDto playerDto = objectMapper.readValue(response.getContentAsString(), PlayerFullInfosDto.class);
        Assertions.assertThat(playerDto).isNotNull();
        Assertions.assertThat(playerDto.id()).isEqualTo(playerId);

        //And
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    public void user_requests_a_single_player_with_an_unknown_id_and_get_NOT_FOUND_and_an_error_message() throws Exception {
        //Given
        Long playerId = 9999L;

        //When
        MockHttpServletResponse response = performCall(String.format("%s/%d", API_URL, playerId));

        //Then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

        //And
        Assertions.assertThat(response.getContentAsString()).contains("Player having id 9999 not found.");
    }

}

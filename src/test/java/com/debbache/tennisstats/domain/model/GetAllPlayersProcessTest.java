package com.debbache.tennisstats.domain.model;

import com.debbache.tennisstats.domain.adapter.GetPlayerByIdQuery;
import com.debbache.tennisstats.domain.adapter.PlayerRepositoryAdapter;
import com.debbache.tennisstats.domain.model.player.GetPlayersProcess;
import com.debbache.tennisstats.domain.model.player.PlayerModel;
import com.debbache.tennisstats.utils.PlayersStubUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPlayersProcessTest {
    @InjectMocks
    GetPlayersProcess sut;
    @Mock
    PlayerRepositoryAdapter repository;

    @Test
    void getAllOrderedByPointsDesc_should_return_all_players_retrieved_by_the_repository() {
        //Given
        List<PlayerModel> players = PlayersStubUtils.stubPlayers();
        when(repository.findAll()).thenReturn(players);
        //When
        List<PlayerModel> expectedPlayers = sut.getAllOrderedByPointsDesc();
        //Then
        Assertions.assertThat(expectedPlayers).isNotNull()
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(players);
    }

    @Test
    void getAllOrderedByPointsDesc_should_return_sorted_players_according_to_their_points_desc() {
        //Given
        List<PlayerModel> players = PlayersStubUtils.stubPlayers();
        when(repository.findAll()).thenReturn(players);
        //When
        List<PlayerModel> expectedPlayers = sut.getAllOrderedByPointsDesc();
        //Then
        Assertions.assertThat(expectedPlayers)
                .isSortedAccordingTo(Comparator.comparingLong(p -> p.data().points()));
    }

    @Test
    void getAllOrderedByPointsDesc_should_return_empty_list_if_the_repository_is_empty() {
        //Given
        when(repository.findAll()).thenReturn(Collections.emptyList());
        //When
        List<PlayerModel> expectedPlayers = sut.getAllOrderedByPointsDesc();
        //Then
        Assertions.assertThat(expectedPlayers)
                .isNotNull()
                .isEmpty();
    }

    @Test
    void getAllOrderedByPointsDesc_should_return_empty_list_if_the_repository_is_null() {
        //Given
        when(repository.findAll()).thenReturn(null);
        //When
        List<PlayerModel> expectedPlayers = sut.getAllOrderedByPointsDesc();
        //Then
        Assertions.assertThat(expectedPlayers)
                .isNotNull()
                .isEmpty();
    }

    @Test
    void getPlayerById_should_throw_exception_when_repository_not_found_the_desired_player() {
        //Given
        Long id = 9999L;
        when(repository.findById(any())).thenReturn(Optional.empty());
        //When
        Exception exception = Assertions.catchException(() -> sut.getPlayerById(new GetPlayerByIdQuery(id)));
        //Then
        Assertions.assertThat(exception)
                .isNotNull()
                .hasMessage("Player having id 9999 not found.");
    }

    @Test
    void getPlayerById_should_return_valid_player_when_repository_contains_the_desired_player() {
        //Given
        PlayerModel player = PlayersStubUtils.stubPlayers().get(0);
        Long id = player.id();
        when(repository.findById(any())).thenReturn(Optional.of(player));
        //When
        PlayerModel expectedPlayer = sut.getPlayerById(new GetPlayerByIdQuery(id));
        //Then
        Assertions.assertThat(expectedPlayer).isNotNull();
        Assertions.assertThat(expectedPlayer.id()).isEqualTo(id);
    }


}

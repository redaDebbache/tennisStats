package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.adapter.PlayerRepositoryAdapter;
import com.debbache.tennisstats.utils.PlayersStubUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStatisticsProcessTest {
    @InjectMocks
    GetStatisticsProcess sut;
    @Mock
    PlayerRepositoryAdapter repository;

    @Test
    public void getAllStatistics_should_throw_exception_when_players_list_from_repository_is_empty() {
        //Given
        when(repository.findAll()).thenReturn(null);
        //When
        Exception exception = Assertions.catchException(() -> sut.getAllStatistics());
        //Then
        Assertions.assertThat(exception.getMessage()).isEqualTo("Required data to compute statistics is missing.");
    }

    @Test
    public void getAllStatistics_should_throw_exception_when_players_list_from_repository_is_null() {
        //Given
        when(repository.findAll()).thenReturn(Collections.emptyList());
        //When
        Exception exception = Assertions.catchException(() -> sut.getAllStatistics());
        //Then
        Assertions.assertThat(exception.getMessage()).isEqualTo("Required data to compute statistics is missing.");
    }

    @Test
    public void getAllStatistics_should_return_statistic_model_players_list_from_repository_is_valid() {
        //Given
        when(repository.findAll()).thenReturn(PlayersStubUtils.stubPlayers());
        //When
        StatisticsModel allStatistics = sut.getAllStatistics();
        //Then
        Assertions.assertThat(allStatistics).isNotNull();
    }
}

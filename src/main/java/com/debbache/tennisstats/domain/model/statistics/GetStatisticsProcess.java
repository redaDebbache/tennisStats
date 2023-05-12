package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.adapter.GetStatisticsAdapter;
import com.debbache.tennisstats.domain.adapter.PlayerRepositoryAdapter;
import com.debbache.tennisstats.domain.exception.MissingPlayersStatisticsException;
import com.debbache.tennisstats.domain.model.player.PlayerModel;

import java.util.List;

public class GetStatisticsProcess implements GetStatisticsAdapter {
    private final PlayerRepositoryAdapter repositoryAdapter;

    public GetStatisticsProcess(PlayerRepositoryAdapter repositoryAdapter) {
        this.repositoryAdapter = repositoryAdapter;
    }

    @Override
    public StatisticsModel getAllStatistics() {
        List<PlayerModel> allPlayers = repositoryAdapter.findAll();
        return StatisticsModel.of(allPlayers)
                .orElseThrow(MissingPlayersStatisticsException::new);
    }
}

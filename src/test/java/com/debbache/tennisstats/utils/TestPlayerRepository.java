package com.debbache.tennisstats.utils;

import com.debbache.tennisstats.domain.adapter.PlayerRepositoryAdapter;
import com.debbache.tennisstats.domain.adapter.PlayerRepositoryFindByIdQuery;
import com.debbache.tennisstats.domain.model.player.PlayerModel;

import java.util.List;
import java.util.Optional;

public class TestPlayerRepository implements PlayerRepositoryAdapter {

    @Override
    public List<PlayerModel> findAll() {
        return PlayersStubUtils.stubPlayers();
    }

    @Override
    public Optional<PlayerModel> findById(PlayerRepositoryFindByIdQuery query) {
        return Optional.empty();
    }
}

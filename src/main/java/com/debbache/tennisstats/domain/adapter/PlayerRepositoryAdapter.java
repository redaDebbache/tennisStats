package com.debbache.tennisstats.domain.adapter;

import com.debbache.tennisstats.domain.model.player.PlayerModel;

import java.util.List;
import java.util.Optional;

public interface PlayerRepositoryAdapter {
    List<PlayerModel> findAll();

    Optional<PlayerModel> findById(PlayerRepositoryFindByIdQuery query);
}

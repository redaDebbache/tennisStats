package com.example.tennisstats.domain.adapter;

import com.example.tennisstats.domain.model.PlayerModel;

import java.util.List;
import java.util.Optional;

public interface PlayerRepositoryAdapter {
    List<PlayerModel> findAll();

    Optional<PlayerModel> findById(PlayerRepositoryFindByIdQuery query);
}

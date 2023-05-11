package com.example.tennisstats.domain.adapter;

import com.example.tennisstats.domain.model.PlayerModel;

import java.util.List;

public interface GetPlayersAdapter {
    List<PlayerModel> getAllOrderedByPointsDesc();

    PlayerModel getPlayerById(GetPlayerByIdQuery query);
}

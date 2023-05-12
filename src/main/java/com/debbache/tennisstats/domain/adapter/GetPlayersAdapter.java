package com.debbache.tennisstats.domain.adapter;

import com.debbache.tennisstats.domain.model.player.PlayerModel;

import java.util.List;

public interface GetPlayersAdapter {
    List<PlayerModel> getAllOrderedByPointsDesc();

    PlayerModel getPlayerById(GetPlayerByIdQuery query);
}

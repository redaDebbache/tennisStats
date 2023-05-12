package com.debbache.tennisstats.domain.model.player;

import com.debbache.tennisstats.domain.adapter.GetPlayerByIdQuery;
import com.debbache.tennisstats.domain.adapter.GetPlayersAdapter;
import com.debbache.tennisstats.domain.adapter.PlayerRepositoryAdapter;
import com.debbache.tennisstats.domain.adapter.PlayerRepositoryFindByIdQuery;
import com.debbache.tennisstats.domain.exception.PlayerNotFoundException;

import java.util.*;
import java.util.stream.Stream;

public class GetPlayersProcess implements GetPlayersAdapter {
    private final PlayerRepositoryAdapter repository;

    public GetPlayersProcess(PlayerRepositoryAdapter repository) {
        this.repository = repository;
    }

    @Override
    public List<PlayerModel> getAllOrderedByPointsDesc() {
        return Optional.ofNullable(repository.findAll())
                .map(Collection::stream)
                .map(s -> s.sorted(Comparator.comparingLong(p -> p.data().points())))
                .map(Stream::toList).orElse(Collections.emptyList());
    }

    @Override
    public PlayerModel getPlayerById(GetPlayerByIdQuery query) {
        return repository.findById(new PlayerRepositoryFindByIdQuery(query.id()))
                .orElseThrow(() -> new PlayerNotFoundException(query.id()));
    }
}

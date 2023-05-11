package com.example.tennisstats.domain.model;

import com.example.tennisstats.domain.adapter.GetPlayerByIdQuery;
import com.example.tennisstats.domain.adapter.GetPlayersAdapter;
import com.example.tennisstats.domain.adapter.PlayerRepositoryAdapter;
import com.example.tennisstats.domain.adapter.PlayerRepositoryFindByIdQuery;
import com.example.tennisstats.domain.exception.PlayerNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class GetPlayersProcess implements GetPlayersAdapter {
    private final PlayerRepositoryAdapter repository;
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

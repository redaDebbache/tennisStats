package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.PlayerModel;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinningCountryRatioStatistic {
    @Getter
    private final WinLosesStatistic winnerCountry;

    public WinningCountryRatioStatistic(List<PlayerModel> players) {
        this.winnerCountry = players.stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(PlayerModel::country,
                        Collectors.flatMapping(player -> player.lastGameResults().stream(), Collectors.toList())))
                .entrySet().stream()
                .map(entry -> new WinLosesStatistic(entry.getKey(), entry.getValue())).max(Comparator.comparing(WinLosesStatistic::getRatio)).orElse(null);
    }

}

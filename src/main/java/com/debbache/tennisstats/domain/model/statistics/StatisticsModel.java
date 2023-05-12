package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.PlayerModel;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

public record StatisticsModel(WinningCountryRatioStatistic mostWinningCountry, BmiPlayerStatistic bmiPlayerStatistic,
                              PlayerHeightMedianStatistic playersHeightMedian) {
    public static Optional<StatisticsModel> of(List<PlayerModel> players) {
        return Optional.ofNullable(players)
                .filter(p -> !CollectionUtils.isEmpty(p))
                .map(p -> new StatisticsModel(new WinningCountryRatioStatistic(p), new BmiPlayerStatistic(p), new PlayerHeightMedianStatistic(p)));
    }
}

package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.PlayerModel;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

public class PlayerHeightMedianStatistic {
    @Getter
    private Integer heightMedian;

    public PlayerHeightMedianStatistic(List<PlayerModel> players) {
        if (!CollectionUtils.isEmpty(players)) {
            List<PlayerModel> sortedPlayers = players.stream().filter(p -> p.getHeight().isPresent()).sorted(Comparator.comparingInt(player -> player.getHeight().get())).toList();
            if (!CollectionUtils.isEmpty(players)) {
                this.heightMedian = sortedPlayers.get((sortedPlayers.size() + 1) / 2).getHeight().orElse(0);
            }
        }
    }
}

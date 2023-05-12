package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.PlayerModel;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class BmiPlayerStatistic {
    @Getter
    private Float averageBmi;

    public BmiPlayerStatistic(List<PlayerModel> players) {
        if (!CollectionUtils.isEmpty(players)) {
            this.averageBmi = BigDecimal.valueOf(players.stream().map(PlayerModel::getBmi).filter(Optional::isPresent).mapToDouble(Optional::get).average().orElse(0)).setScale(1, RoundingMode.HALF_UP).floatValue();
        }
    }
}

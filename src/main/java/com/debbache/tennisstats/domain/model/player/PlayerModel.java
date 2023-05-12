package com.debbache.tennisstats.domain.model.player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record PlayerModel(Long id, String firstName, String lastName, String shortName, String picture, String sex,
                          CountryModel country, PlayerDataModel data) {
    public List<Integer> lastGameResults() {
        return Optional.ofNullable(data()).map(PlayerDataModel::last).orElse(Collections.emptyList());
    }

    public Optional<Integer> getWeight() {
        return Optional.ofNullable(data()).map(PlayerDataModel::weight).filter(weight -> weight > 0);
    }

    public Optional<Integer> getHeight() {
        return Optional.ofNullable(data()).map(PlayerDataModel::height).filter(height -> height > 0);
    }

    public Optional<Float> getBmi() {
        return getHeight().flatMap(height -> getWeight().map(weight -> BigDecimal.valueOf(weight / (Math.pow(height, 2)) * 10).setScale(1, RoundingMode.HALF_UP).floatValue()));
    }
}

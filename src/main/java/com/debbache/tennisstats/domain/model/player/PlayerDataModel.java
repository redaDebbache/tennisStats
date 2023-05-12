package com.debbache.tennisstats.domain.model.player;

import java.util.List;

public record PlayerDataModel(Integer rank, Long points, Integer weight, Integer height, Integer age,
                              List<Integer> last) {
}

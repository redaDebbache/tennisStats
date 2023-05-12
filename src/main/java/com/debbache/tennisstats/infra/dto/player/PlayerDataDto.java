package com.debbache.tennisstats.infra.dto.player;

import java.util.List;

public record PlayerDataDto(Integer rank, Long points, Integer weight, Integer height, Integer age,
                            List<Integer> last) {
}

package com.debbache.tennisstats.domain.model.statistics;

import com.debbache.tennisstats.domain.model.player.CountryModel;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
public class WinLosesStatistic {
    private CountryModel country;
    private Integer wins;
    private Integer loses;
    private Float ratio;

    public WinLosesStatistic(CountryModel country, List<Integer> allGamesResults) {
        if (!CollectionUtils.isEmpty(allGamesResults)) {
            this.country = country;
            this.wins = (int) allGamesResults.stream().filter(result -> 1 == result).count();
            this.loses = allGamesResults.size() - this.wins;

            // The ratio is computed using the formula: ratio in percentages =  W / W+L
            //See https://fr.wikipedia.org/wiki/Ratio_victoires-d%C3%A9faites
            this.ratio = BigDecimal.valueOf(this.wins * 100L).divide(BigDecimal.valueOf(allGamesResults.size()), RoundingMode.HALF_UP).floatValue();
        }
    }

}

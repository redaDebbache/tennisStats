package com.debbache.tennisstats.infra.config;

import com.debbache.tennisstats.domain.adapter.GetPlayersAdapter;
import com.debbache.tennisstats.domain.adapter.GetStatisticsAdapter;
import com.debbache.tennisstats.domain.adapter.PlayerRepositoryAdapter;
import com.debbache.tennisstats.domain.model.player.GetPlayersProcess;
import com.debbache.tennisstats.domain.model.statistics.GetStatisticsProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayersConfiguration {

    @Bean
    public GetPlayersAdapter getAllPlayersAdapter(PlayerRepositoryAdapter repository) {
        return new GetPlayersProcess(repository);
    }

    @Bean
    public GetStatisticsAdapter getStatisticsAdapter(PlayerRepositoryAdapter repository) {
        return new GetStatisticsProcess(repository);
    }

}

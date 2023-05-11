package com.example.tennisstats.infra.config;

import com.example.tennisstats.domain.adapter.GetPlayersAdapter;
import com.example.tennisstats.domain.adapter.PlayerRepositoryAdapter;
import com.example.tennisstats.domain.model.GetPlayersProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayersConfiguration {

    @Bean
    public GetPlayersAdapter getAllPlayersAdapter(PlayerRepositoryAdapter repository) {
        return new GetPlayersProcess(repository);
    }

}

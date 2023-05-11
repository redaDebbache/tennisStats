package com.example.tennisstats.infra.repository;

import com.example.tennisstats.domain.adapter.PlayerRepositoryAdapter;
import com.example.tennisstats.domain.adapter.PlayerRepositoryFindByIdQuery;
import com.example.tennisstats.domain.model.PlayerModel;
import com.example.tennisstats.domain.model.PlayerWareHouseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerRepository implements PlayerRepositoryAdapter {
    private final ObjectMapper objectMapper;
    @Getter
    private PlayerWareHouseModel wareHouse;

    @PostConstruct
    @SneakyThrows
    public void setUp() {
        Path file = ResourceUtils.getFile("classpath:data/players.json").toPath();
        this.wareHouse = objectMapper.readValue(file.toFile(), PlayerWareHouseModel.class);
    }

    @Override
    public List<PlayerModel> findAll() {
        return this.wareHouse.players();
    }

    @Override
    public Optional<PlayerModel> findById(PlayerRepositoryFindByIdQuery query) {
        return this.wareHouse.players().stream()
                .filter(player -> player.id() != null)
                .filter(player -> query.playerId().equals(player.id())).findFirst();
    }
}

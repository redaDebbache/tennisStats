package com.debbache.tennisstats.utils;

import com.debbache.tennisstats.domain.model.player.PlayerModel;
import com.debbache.tennisstats.domain.model.player.PlayerWareHouseModel;
import com.debbache.tennisstats.infra.dto.player.PlayerDto;
import com.debbache.tennisstats.infra.mapper.PlayerRestMapper;
import com.debbache.tennisstats.infra.mapper.PlayerRestMapperImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.nio.charset.Charset;
import java.util.List;

@UtilityClass
public class PlayersStubUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final PlayerRestMapper MAPPER = new PlayerRestMapperImpl();
    private static final Resource PLAYERS_RESOURCE = new ClassPathResource("/data/players.json");

    @SneakyThrows
    public static List<PlayerModel> stubPlayers() {
        PlayerWareHouseModel playerWareHouseModel = OBJECT_MAPPER.readValue(PLAYERS_RESOURCE.getContentAsString(Charset.defaultCharset()), PlayerWareHouseModel.class);
        return playerWareHouseModel.players();
    }

    @SneakyThrows
    public static List<PlayerDto> stubPlayerDtos() {
        return stubPlayers().stream().map(MAPPER::map).toList();
    }

}

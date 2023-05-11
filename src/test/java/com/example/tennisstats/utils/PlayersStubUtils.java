package com.example.tennisstats.utils;

import com.example.tennisstats.domain.model.PlayerModel;
import com.example.tennisstats.domain.model.PlayerWareHouseModel;
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
    private static final Resource PLAYERS_RESOURCE = new ClassPathResource("/data/players.json");
@SneakyThrows
    public static List<PlayerModel> stubPlayers(){
    PlayerWareHouseModel playerWareHouseModel = OBJECT_MAPPER.readValue(PLAYERS_RESOURCE.getContentAsString(Charset.defaultCharset()), PlayerWareHouseModel.class);
    return playerWareHouseModel.players();
}
}

package com.example.tennisstats.infra.rest;

import com.example.tennisstats.domain.adapter.GetPlayerByIdQuery;
import com.example.tennisstats.domain.adapter.GetPlayersAdapter;
import com.example.tennisstats.domain.model.PlayerModel;
import com.example.tennisstats.infra.dto.PlayerDto;
import com.example.tennisstats.infra.dto.PlayersResponse;
import com.example.tennisstats.infra.mapper.PlayerRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
@Log4j2
public class PlayerStatsController {
private final GetPlayersAdapter getPlayersAdapter;
private final PlayerRestMapper mapper;
    @GetMapping
    @Tag(name = "All Players")
    @Operation(summary = "Returns all available players ordered from best to worst.", description = "The order criterion is defined by the number of points obtained by each player. This data being the property of an underlying object, to access it, it is recommended to follow the GET full infos link provided for each player to obtain their complete information."
            )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success: Either returns available players or players are empty",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class)) }),
            @ApiResponse(responseCode = "5xx", description = "An unexpected error occurred",
                    content = @Content)})
    public PlayersResponse getAllPlayers(){
        log.debug("Requesting all players");
        List<PlayerModel> playerModels = getPlayersAdapter.getAllOrderedByPointsDesc();
        return mapper.mapToResponse(playerModels);
    }

    @GetMapping("/{id}")
    @Tag(name = "All Players")
    @Operation(summary = "Returns all available players ordered from best to worst.", description = "The order criterion is defined by the number of points obtained by each player. This data being the property of an underlying object, to access it, it is recommended to follow the GET full infos link provided for each player to obtain their complete information."
            )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success: Either returns available players or players are empty",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class)) }),
            @ApiResponse(responseCode = "5xx", description = "An unexpected error occurred",
                    content = @Content)})
    public PlayerDto getPlayer(@PathVariable("id") Long id){
        log.debug("Requesting all players");
        PlayerModel playerModel = getPlayersAdapter.getPlayerById(new GetPlayerByIdQuery(id));
        return mapper.map(playerModel);
    }
}

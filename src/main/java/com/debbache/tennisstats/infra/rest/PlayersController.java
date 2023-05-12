package com.debbache.tennisstats.infra.rest;

import com.debbache.tennisstats.domain.adapter.GetPlayerByIdQuery;
import com.debbache.tennisstats.domain.adapter.GetPlayersAdapter;
import com.debbache.tennisstats.domain.model.player.PlayerModel;
import com.debbache.tennisstats.infra.dto.player.PlayerDto;
import com.debbache.tennisstats.infra.dto.player.PlayerFullInfosDto;
import com.debbache.tennisstats.infra.dto.player.PlayersResponse;
import com.debbache.tennisstats.infra.mapper.PlayerRestMapper;
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
public class PlayersController {
    private final GetPlayersAdapter getPlayersAdapter;
    private final PlayerRestMapper mapper;

    @GetMapping
    @Tag(name = "All Players")
    @Operation(summary = "Request all available players ordered from best to worst.", description = "The order criterion is defined by the number of points obtained by each player. This data being the property of an underlying object, to access it, it is recommended to follow the GET full infos link provided for each player to obtain their complete information."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success: Either returns available players or players are empty",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class))}),
            @ApiResponse(responseCode = "5xx", description = "An unexpected error occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    public PlayersResponse getAllPlayers() {
        log.debug("Requesting all players");
        List<PlayerModel> playerModels = getPlayersAdapter.getAllOrderedByPointsDesc();
        return mapper.mapToResponse(playerModels);
    }

    @GetMapping("/{id}")
    @Tag(name = "All Players")
    @Operation(summary = "Request a player identified by it's id.", description = "The server will return the player full infos, or throw an 404 error otherwiser"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success: A valid Player is returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class))}),

            @ApiResponse(responseCode = "404", description = "Player not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "5xx", description = "An unexpected error occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    public PlayerFullInfosDto getPlayer(@PathVariable("id") Long id) {
        log.debug("Requesting a single player with id {}", id);
        PlayerModel playerModel = getPlayersAdapter.getPlayerById(new GetPlayerByIdQuery(id));
        return mapper.mapFullInfos(playerModel);
    }
}

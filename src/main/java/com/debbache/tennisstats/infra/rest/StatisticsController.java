package com.debbache.tennisstats.infra.rest;

import com.debbache.tennisstats.domain.adapter.GetStatisticsAdapter;
import com.debbache.tennisstats.domain.model.statistics.StatisticsModel;
import com.debbache.tennisstats.infra.dto.player.PlayerDto;
import com.debbache.tennisstats.infra.dto.sttistics.StatisticsDto;
import com.debbache.tennisstats.infra.mapper.StatisticsMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@Log4j2
public class StatisticsController {
    private final GetStatisticsAdapter getStatisticsAdapter;
    private final StatisticsMapper mapper;

    @GetMapping
    @Tag(name = "Statistics")
    @Operation(summary = "Request players statistics", description = "returns the following statistics:\n" +
            "- Country with the highest win ratio\n" +
            "- Average BMI of all players\n" +
            "- The median height of the players"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success: Statistics are returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDto.class))}),
            @ApiResponse(responseCode = "5xx", description = "An unexpected error occurred",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)))})
    public StatisticsDto getAllPlayers() {
        log.debug("Requesting players statistics");
        StatisticsModel statisticsModel = getStatisticsAdapter.getAllStatistics();
        return mapper.map(statisticsModel);
    }
}

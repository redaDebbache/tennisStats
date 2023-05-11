package com.example.tennisstats.infra.mapper;

import com.example.tennisstats.domain.model.CountryModel;
import com.example.tennisstats.domain.model.PlayerDataModel;
import com.example.tennisstats.domain.model.PlayerModel;
import com.example.tennisstats.infra.dto.CountryDto;
import com.example.tennisstats.infra.dto.PlayerDataDto;
import com.example.tennisstats.infra.dto.PlayerDto;
import com.example.tennisstats.infra.dto.PlayersResponse;
import com.example.tennisstats.infra.hatoasLink.ResourceLink;
import com.example.tennisstats.infra.rest.PlayerStatsController;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.hateoas.Link;

import java.util.*;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface PlayerRestMapper {

    PlayerDataDto map(PlayerDataModel source);

    CountryDto map(CountryModel source);

    @Mapping(target = "fullInfos", source ="id", qualifiedByName = "playerLink")
    @Mapping(target = "rank", source ="data.rank")
    @Mapping(target = "points", source ="data.points")
    @Mapping(target = "country", source ="country.code")
    PlayerDto map(PlayerModel source);

    @Named("playerLink")
    default ResourceLink playerLink(Long id){
        Link link = linkTo(PlayerStatsController.class).slash(id.toString()).withSelfRel();
        return new ResourceLink(link.getRel().value(), link.getHref());
    }
    List<PlayerDto> mapAll(List<PlayerModel> source);
   default PlayersResponse mapToResponse(List<PlayerModel> playerModels){
       return new PlayersResponse(firstNonNull(mapAll(playerModels), Collections.emptyList()));
   }
}

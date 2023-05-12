package com.debbache.tennisstats.infra.mapper;

import com.debbache.tennisstats.domain.model.player.CountryModel;
import com.debbache.tennisstats.domain.model.player.PlayerDataModel;
import com.debbache.tennisstats.domain.model.player.PlayerModel;
import com.debbache.tennisstats.infra.dto.player.*;
import com.debbache.tennisstats.infra.hatoasLink.ResourceLink;
import com.debbache.tennisstats.infra.rest.PlayersController;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.hateoas.Link;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Mapper(componentModel = "spring")
public interface PlayerRestMapper {

    PlayerDataDto map(PlayerDataModel source);

    CountryDto map(CountryModel source);

    @Mapping(target = "fullInfos", source = "id", qualifiedByName = "playerLink")
    @Mapping(target = "rank", source = "data.rank")
    @Mapping(target = "points", source = "data.points")
    @Mapping(target = "country", source = "country.code")
    PlayerDto map(PlayerModel source);

    @Named("playerLink")
    default ResourceLink playerLink(Long id) {
        Link link = linkTo(PlayersController.class).slash(id.toString()).withSelfRel();
        return new ResourceLink(link.getRel().value(), link.getHref());
    }

    List<PlayerDto> mapAll(List<PlayerModel> source);

    default PlayersResponse mapToResponse(List<PlayerModel> playerModels) {
        return new PlayersResponse(firstNonNull(mapAll(playerModels), Collections.emptyList()));
    }

    PlayerFullInfosDto mapFullInfos(PlayerModel playerModel);
}

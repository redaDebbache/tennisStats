package com.debbache.tennisstats.infra.mapper;

import com.debbache.tennisstats.domain.model.player.CountryModel;
import com.debbache.tennisstats.domain.model.player.PlayerDataModel;
import com.debbache.tennisstats.domain.model.player.PlayerModel;
import com.debbache.tennisstats.infra.dto.player.*;
import com.debbache.tennisstats.infra.hatoasLink.ResourceLink;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlayerRestMapperTest {
    private final PlayerRestMapper mapper = new PlayerRestMapperImpl();

    private static void assertPlayerDtoMappingValidity(PlayerDto firstPlayerDto, PlayerModel firstPlayerModel) {
        Assertions.assertThat(firstPlayerDto).isNotNull();
        Assertions.assertThat(firstPlayerDto.firstName()).isEqualTo(firstPlayerModel.firstName());
        Assertions.assertThat(firstPlayerDto.lastName()).isEqualTo(firstPlayerModel.lastName());
        Assertions.assertThat(firstPlayerDto.sex()).isEqualTo(firstPlayerModel.sex());
        Assertions.assertThat(firstPlayerDto.points()).isEqualTo(firstPlayerModel.data().points());
        Assertions.assertThat(firstPlayerDto.rank()).isEqualTo(firstPlayerModel.data().rank());
        Assertions.assertThat(firstPlayerDto.fullInfos().rel()).isEqualTo("self");
        Assertions.assertThat(firstPlayerDto.fullInfos().href()).isEqualTo(String.format("/api/v1/players/%d", firstPlayerModel.id()));
    }

    @Test
    void should_map_country_model_to_dto() {
        //Given
        CountryModel source = new CountryModel("picture-url", "ESP");
        //When
        CountryDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(target.picture()).isEqualTo(source.picture());
        Assertions.assertThat(target.code()).isEqualTo(source.code());
    }

    @Test
    void map_country_should_return_null_when_source_is_null() {
        //Given
        CountryModel source = null;
        //When
        CountryDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNull();
    }

    @Test
    void should_map_player_data_model_to_dto() {
        //Given
        PlayerDataModel source = new PlayerDataModel(1, 200L, 8000, 180, 35, List.of(1, 0, 1, 0, 1, 1));
        //When
        PlayerDataDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(target.rank()).isEqualTo(source.rank());
        Assertions.assertThat(target.points()).isEqualTo(source.points());
        Assertions.assertThat(target.weight()).isEqualTo(source.weight());
        Assertions.assertThat(target.height()).isEqualTo(source.height());
        Assertions.assertThat(target.age()).isEqualTo(source.age());
        Assertions.assertThat(target.last()).isEqualTo(source.last());
    }

    @Test
    void map_player_data_should_return_null_when_source_is_null() {
        //Given
        PlayerDataModel source = null;
        //When
        PlayerDataDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNull();
    }

    @Test
    void should_map_player_model_to_dto() {
        //Given
        PlayerDataModel dataModel = new PlayerDataModel(1, 200L, 8000, 180, 35, List.of(1, 0, 1, 0, 1, 1));
        CountryModel country = new CountryModel("picture-url", "ESP");

        PlayerModel source = new PlayerModel(1L, "John", "Doe", "J.D", "player-picture", "M", country, dataModel);

        //When
        PlayerDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(target.firstName()).isEqualTo(source.firstName());
        Assertions.assertThat(target.lastName()).isEqualTo(source.lastName());
        Assertions.assertThat(target.sex()).isEqualTo(source.sex());
        Assertions.assertThat(target.points()).isEqualTo(dataModel.points());
        Assertions.assertThat(target.rank()).isEqualTo(dataModel.rank());
        Assertions.assertThat(target.fullInfos().rel()).isEqualTo("self");
        Assertions.assertThat(target.fullInfos().href()).isEqualTo(String.format("/api/v1/players/%d", source.id()));
    }

    @Test
    void map_player_should_return_null_when_source_is_null() {
        //Given
        PlayerModel source = null;
        //When
        PlayerDto target = mapper.map(source);
        //Then
        Assertions.assertThat(target).isNull();
    }

    @Test
    void should_map_player_model_to_full_infos_dto() {
        //Given
        PlayerDataModel dataModel = new PlayerDataModel(1, 200L, 8000, 180, 35, List.of(1, 0, 1, 0, 1, 1));
        CountryModel country = new CountryModel("picture-url", "ESP");

        PlayerModel source = new PlayerModel(1L, "John", "Doe", "J.D", "player-picture", "M", country, dataModel);

        //When
        PlayerFullInfosDto target = mapper.mapFullInfos(source);
        //Then
        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(target.firstName()).isEqualTo(source.firstName());
        Assertions.assertThat(target.lastName()).isEqualTo(source.lastName());
        Assertions.assertThat(target.sex()).isEqualTo(source.sex());
        Assertions.assertThat(target.data().points()).isEqualTo(dataModel.points());
        Assertions.assertThat(target.data().rank()).isEqualTo(dataModel.rank());
        Assertions.assertThat(target.data().age()).isEqualTo(dataModel.age());
        Assertions.assertThat(target.data().weight()).isEqualTo(dataModel.weight());
        Assertions.assertThat(target.data().height()).isEqualTo(dataModel.height());
        Assertions.assertThat(target.data().last()).isEqualTo(dataModel.last());
        Assertions.assertThat(target.country().code()).isEqualTo(country.code());
        Assertions.assertThat(target.country().picture()).isEqualTo(country.picture());

    }

    @Test
    void mapToFullDataInfos_should_return_null_when() {
        //Given
        PlayerDataModel dataModel = new PlayerDataModel(1, 200L, 8000, 180, 35, List.of(1, 0, 1, 0, 1, 1));
        CountryModel country = new CountryModel("picture-url", "ESP");

        PlayerModel source = new PlayerModel(1L, "John", "Doe", "J.D", "player-picture", "M", country, dataModel);

        //When
        PlayerFullInfosDto target = mapper.mapFullInfos(source);
        //Then
        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(target.firstName()).isEqualTo(source.firstName());
        Assertions.assertThat(target.lastName()).isEqualTo(source.lastName());
        Assertions.assertThat(target.sex()).isEqualTo(source.sex());
        Assertions.assertThat(target.data().points()).isEqualTo(dataModel.points());
        Assertions.assertThat(target.data().rank()).isEqualTo(dataModel.rank());
        Assertions.assertThat(target.data().age()).isEqualTo(dataModel.age());
        Assertions.assertThat(target.data().weight()).isEqualTo(dataModel.weight());
        Assertions.assertThat(target.data().height()).isEqualTo(dataModel.height());
        Assertions.assertThat(target.data().last()).isEqualTo(dataModel.last());
        Assertions.assertThat(target.country().code()).isEqualTo(country.code());
        Assertions.assertThat(target.country().picture()).isEqualTo(country.picture());
    }

    @Test
    void mapAll_should_map_list_of_player_model_to_a_list_of_player_dto() {
        //Given
        PlayerDataModel dataModel = new PlayerDataModel(1, 200L, 8000, 180, 35, List.of(1, 0, 1, 0, 1, 1));
        CountryModel country = new CountryModel("picture-url", "ESP");

        List<PlayerModel> source = List.of(
                new PlayerModel(1L, "Al", "Bay", "A.B", "player-picture-1", "M", country, dataModel),
                new PlayerModel(2L, "Bee", "Clay", "B.C", "player-picture-2", "F", country, dataModel)
        );

        //When
        List<PlayerDto> target = mapper.mapAll(source);
        //Then
        Assertions.assertThat(target).isNotEmpty().hasSize(source.size());
        PlayerDto firstPlayerDto = target.get(0);
        PlayerModel firstPlayerModel = source.get(0);
        assertPlayerDtoMappingValidity(firstPlayerDto, firstPlayerModel);

        PlayerDto secondPlayerDto = target.get(1);
        PlayerModel secondPlayerModel = source.get(1);
        assertPlayerDtoMappingValidity(secondPlayerDto, secondPlayerModel);
    }

    @Test
    void mapAll_should_return_null_when_source_is_null() {
        //Given
        List<PlayerModel> source = null;
        //When
        List<PlayerDto> target = mapper.mapAll(source);
        //Then
        Assertions.assertThat(target).isNull();
    }

    @Test
    void mapToResponse_should_map_list_of_player_model_to_a_player_response_dto() {
        //Given
        PlayerDataModel dataModel = new PlayerDataModel(1, 200L, 8000, 180, 35, List.of(1, 0, 1, 0, 1, 1));
        CountryModel country = new CountryModel("picture-url", "ESP");

        List<PlayerModel> source = List.of(
                new PlayerModel(1L, "Al", "Bay", "A.B", "player-picture-1", "M", country, dataModel),
                new PlayerModel(2L, "Bee", "Clay", "B.C", "player-picture-2", "F", country, dataModel)
        );

        //When
        PlayersResponse target = mapper.mapToResponse(source);
        //Then
        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(target.players()).isNotEmpty().hasSize(source.size());
        PlayerDto firstPlayerDto = target.players().get(0);
        PlayerModel firstPlayerModel = source.get(0);
        assertPlayerDtoMappingValidity(firstPlayerDto, firstPlayerModel);

        PlayerDto secondPlayerDto = target.players().get(1);
        PlayerModel secondPlayerModel = source.get(1);
        assertPlayerDtoMappingValidity(secondPlayerDto, secondPlayerModel);
    }

    @Test
    void mapToResponse_should_return_non_null_response_containing_empty_list_when_source_is_null() {
        //Given
        List<PlayerModel> source = null;
        //When
        PlayersResponse target = mapper.mapToResponse(source);
        //Then
        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(target.players()).isEmpty();
    }

    @Test
    void playerLink_should_create_a_player_link_from_id() {
        //Given
        Long id = 1L;
        //When
        ResourceLink resourceLink = mapper.playerLink(id);
        //Then
        Assertions.assertThat(resourceLink).isNotNull();
        Assertions.assertThat(resourceLink.rel()).isEqualTo("self");
        Assertions.assertThat(resourceLink.href()).isEqualTo(String.format("/api/v1/players/%d", id));
    }
}

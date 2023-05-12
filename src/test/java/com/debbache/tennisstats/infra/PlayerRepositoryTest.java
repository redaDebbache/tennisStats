package com.debbache.tennisstats.infra;

import com.debbache.tennisstats.domain.adapter.PlayerRepositoryFindByIdQuery;
import com.debbache.tennisstats.domain.model.player.PlayerModel;
import com.debbache.tennisstats.domain.model.player.PlayerWareHouseModel;
import com.debbache.tennisstats.infra.repository.PlayerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository sut;

    @Test
    public void should_load_players_from_resources_on_set_up() {
        //When
        PlayerWareHouseModel wareHouse = sut.getWareHouse();
        //Then
        Assertions.assertThat(wareHouse).isNotNull();
        Assertions.assertThat(wareHouse.players()).isNotEmpty().hasSize(5);
    }

    @Test
    public void findAll_should_return_all_players() {
        //Given
        PlayerWareHouseModel wareHouse = sut.getWareHouse();
        //When
        List<PlayerModel> allPlayers = sut.findAll();
        //Then
        Assertions.assertThat(allPlayers).isNotEmpty()
                .isEqualTo(wareHouse.players());
    }


    @Test
    public void findById_should_return_optional_empty_when_warehouse_does_not_contain_desired_player() {
        //Given
        Long id = 9999L;
        //When
        Optional<PlayerModel> expectedPlayer = sut.findById(new PlayerRepositoryFindByIdQuery(id));
        //Then
        Assertions.assertThat(expectedPlayer).isEmpty();
    }

    @Test
    public void findById_should_return_valid_player_when_warehouse_contains_it() {
        //Given
        Long id = 52L;
        //When
        Optional<PlayerModel> expectedPlayer = sut.findById(new PlayerRepositoryFindByIdQuery(id));
        //Then
        Assertions.assertThat(expectedPlayer).isNotEmpty();
    }


}

package com.example.tennisstats.stepDefinitions;

import com.example.tennisstats.config.CucumberSpringConfiguration;
import com.example.tennisstats.domain.model.PlayerModel;
import com.example.tennisstats.infra.dto.PlayerDto;
import com.example.tennisstats.stepDefinitions.context.PlayerContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import static com.example.tennisstats.utils.PlayersStubUtils.stubPlayers;

public class PlayerStepDefs extends CucumberSpringConfiguration {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    private static final String API_URL = "/stats/api/v1/players";
    private PlayerContext context = new PlayerContext();
    @When("The user requests all players")
    public void userRequestsAllPlayers() throws Exception {
        performCall(API_URL);
    }

    @Then("A valid list of players is returned")
    public void aValidListOfPlayersIsReturned() {
        Assertions.assertThat(context.getResponse()).isNotNull()
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(stubPlayers());
    }

    @And("The user receives a {int} response")
    public void theUserReceivesAResponse(int status) {
        Assertions.assertThat(context.getResponse().getStatus()).isEqualTo(status);
    }

    @When("The user requests a player having id {long}")
    public void theUserRequestsAPlayerHavingId(long playerId) throws Exception {
        performCall(String.format("%s/%d", API_URL, playerId));
    }

    private void performCall(String url) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                .andReturn().getResponse();
        context.setResponse(response);
    }

    @And("A valid player having id {long} is returned")
    public void aValidPlayerIsReturned(long id) throws Exception {
        PlayerDto playerDto = objectMapper.readValue(context.getResponse().getContentAsString(), PlayerDto.class);
        Assertions.assertThat(playerDto).isNotNull();
        Assertions.assertThat(playerDto.id()).isEqualTo(id);

    }

    @And("An error message containing {string}")
    public void anErrorMessageContaining(String errorMessage) throws Exception{
        Assertions.assertThat(context.getResponse().getContentAsString()).contains(errorMessage);
    }
}

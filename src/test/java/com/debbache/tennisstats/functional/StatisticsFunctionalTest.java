package com.debbache.tennisstats.functional;

import com.debbache.tennisstats.infra.dto.sttistics.StatisticsDto;
import com.debbache.tennisstats.utils.StatisticsStubUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

@SpringBootTest
public class StatisticsFunctionalTest extends FunctionalTest {
    private static final String API_URL = "/api/v1/statistics";

    @Test
    public void user_requests_all_player_statistics_and_get_SUCCESS_and_valid_result() throws Exception {
        //When
        MockHttpServletResponse response = performCall(API_URL);

        //Then
        StatisticsDto statisticsDto = objectMapper.readValue(response.getContentAsString(), StatisticsDto.class);
        Assertions.assertThat(statisticsDto).isNotNull()
                .isEqualTo(StatisticsStubUtils.stubStatisticsDto());

        //And
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}

package com.debbache.tennisstats.utils;

import com.debbache.tennisstats.domain.adapter.GetStatisticsAdapter;
import com.debbache.tennisstats.domain.model.statistics.GetStatisticsProcess;
import com.debbache.tennisstats.domain.model.statistics.StatisticsModel;
import com.debbache.tennisstats.infra.dto.sttistics.StatisticsDto;
import com.debbache.tennisstats.infra.mapper.StatisticsMapper;
import com.debbache.tennisstats.infra.mapper.StatisticsMapperImpl;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StatisticsStubUtils {
    private static final GetStatisticsAdapter GET_STATISTICS_ADAPTER = new GetStatisticsProcess(new TestPlayerRepository());
    private static final StatisticsMapper MAPPER = new StatisticsMapperImpl();

    public static StatisticsModel stubStatisticsModel() {
        return GET_STATISTICS_ADAPTER.getAllStatistics();
    }

    public static StatisticsDto stubStatisticsDto() {
        return MAPPER.map(stubStatisticsModel());
    }

}

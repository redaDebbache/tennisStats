package com.debbache.tennisstats.config;

import com.debbache.tennisstats.TennisStatsApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@ContextConfiguration(classes = {TestConfiguration.class, TennisStatsApplication.class})
public class TestConfiguration {

    @Bean
    MockMvc mockMvc(WebApplicationContext webApplicationContext) {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}

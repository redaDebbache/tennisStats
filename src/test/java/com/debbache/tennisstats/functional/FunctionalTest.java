package com.debbache.tennisstats.functional;

import com.debbache.tennisstats.TennisStatsApplication;
import com.debbache.tennisstats.config.TestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(classes = {TestConfiguration.class, TennisStatsApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FunctionalTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


    protected MockHttpServletResponse performCall(String url) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(requestBuilder)
                .andReturn().getResponse();

    }


}

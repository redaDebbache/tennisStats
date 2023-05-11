package com.example.tennisstats.stepDefinitions.context;

import jakarta.servlet.ServletContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.mock.web.MockHttpServletResponse;
@Getter
@Setter
public class PlayerContext {
    private MockHttpServletResponse response;
}

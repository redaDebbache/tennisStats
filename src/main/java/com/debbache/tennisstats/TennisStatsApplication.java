package com.debbache.tennisstats;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Tennis players stats application", version = "1.0", description = "We are able to share tennis player stats all over the world!!"))
public class TennisStatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TennisStatsApplication.class, args);
    }

}

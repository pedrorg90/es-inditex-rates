package com.inditex.testjava2020.esinditexrates.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Rates API")
                        .description("Se encarga de buscar la tarifa más adecuada para un producto")
                        .version("1.0"));
    }
}
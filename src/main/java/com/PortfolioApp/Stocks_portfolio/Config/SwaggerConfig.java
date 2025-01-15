package com.PortfolioApp.Stocks_portfolio.Config;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info().title("Portfolio API")
                        .description("By Ankit Sankhyan")
        ).servers(List.of(new Server().url("http://localhost:8080").description("Local Server")));


    }
}
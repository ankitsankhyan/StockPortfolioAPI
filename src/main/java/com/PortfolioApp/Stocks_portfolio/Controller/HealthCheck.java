package com.PortfolioApp.Stocks_portfolio.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Testing API")
@RestController
public class HealthCheck {
    @GetMapping
    public String getStatus(){
        return "I am working fine";
    }
}

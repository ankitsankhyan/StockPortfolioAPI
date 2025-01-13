package com.PortfolioApp.Stocks_portfolio.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping
    public String getStatus(){
        return "I am working fine";
    }
}

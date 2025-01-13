package com.PortfolioApp.Stocks_portfolio.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer openPrice;
    private Integer closePrice;
    private Integer highPrice;
    private Integer lowPrice;
    private Integer settlementPrice;

}

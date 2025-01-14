package com.PortfolioApp.Stocks_portfolio.dto;

import com.PortfolioApp.Stocks_portfolio.Controller.StockController;
import com.PortfolioApp.Stocks_portfolio.Entities.StockHolding;

import java.util.List;

public class UserWithHoldingDTO {
    private String name;
    private String email;
    private List<StockHolding> stockHoldingList;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

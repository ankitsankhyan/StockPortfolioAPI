package com.PortfolioApp.Stocks_portfolio.dto;

import com.PortfolioApp.Stocks_portfolio.Controller.ExchangeController;
import com.PortfolioApp.Stocks_portfolio.Entities.TransactionType;

public class ExchangeDTO {
    private Integer userId;
    private TransactionType type;
    private double quantity;
    private int stockId;
    public  ExchangeDTO(){

    }

    public ExchangeDTO(Integer userId, TransactionType type, int stockId, double quantity) {
        this.userId = userId;
        this.type = type;
        this.stockId = stockId;
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }
}

package com.PortfolioApp.Stocks_portfolio.dto;

public class StockHoldingDTO {

    private int stockId;
    private double netQuantity;
    private double netPrice;
    private double currentPrice;
    private double profit;

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public double getNetQuantity() {
        return netQuantity;
    }

    public void setNetQuantity(double netQuantity) {
        this.netQuantity = netQuantity;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}

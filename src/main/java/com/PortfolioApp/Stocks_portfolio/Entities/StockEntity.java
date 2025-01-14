package com.PortfolioApp.Stocks_portfolio.Entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
@Entity
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int stockId;
     private String name;
     private Double openPrice;
     private Double closePrice;
     private Double highPrice;
     private Double lowPrice;
     private Double settlementPrice;

    public StockEntity(String name, Double openPrice, Double closePrice, Double highPrice, Double lowPrice, Double settlementPrice) {
        this.name = name;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.settlementPrice = settlementPrice;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "stockId=" + stockId +
                ", name='" + name + '\'' +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", settlementPrice=" + settlementPrice +
                '}';
    }
}

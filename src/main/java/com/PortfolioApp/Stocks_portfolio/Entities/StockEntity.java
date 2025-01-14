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
     private int id;
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
    public StockEntity(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getSettlementPrice() {
        return settlementPrice;
    }

    public void setSettlementPrice(Double settlementPrice) {
        this.settlementPrice = settlementPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", settlementPrice=" + settlementPrice +
                '}';
    }
}

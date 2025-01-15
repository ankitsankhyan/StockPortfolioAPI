package com.PortfolioApp.Stocks_portfolio.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Entity
@Table(name = "stock_holding", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "stock_id"}))
public class StockHolding {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double price;
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private StockEntity stock;

    public double getPrice() {
     return price;
    }

    public void setPrice(double price) {
     this.price = price;
    }

    public double getQuantity() {
     return quantity;
    }

    public void setQuantity(double quantity) {
     this.quantity = quantity;
    }

    public UserEntity getUser() {
     return user;
    }

    public void setUser(UserEntity user) {
     this.user = user;
    }

    public StockEntity getStock() {
     return stock;
    }

    public void setStock(StockEntity stock) {
     this.stock = stock;
    }
}

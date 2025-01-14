package com.PortfolioApp.Stocks_portfolio.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

@Entity
public class ExchangeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false) // Foreign key column in the database
    private StockEntity stock;

    // Each exchange is associated with one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column in the database
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private  TransactionType type;

    private Double quantity;
    private Double price;

    public StockEntity getStock() {
        return stock;
    }

    public void setStock(StockEntity stock) {
        this.stock = stock;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

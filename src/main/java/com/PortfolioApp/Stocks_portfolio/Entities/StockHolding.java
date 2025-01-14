package com.PortfolioApp.Stocks_portfolio.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
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
    private UserEntity u;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private StockEntity s;
}

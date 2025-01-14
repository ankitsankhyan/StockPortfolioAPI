package com.PortfolioApp.Stocks_portfolio.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;
@Data
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

    private Integer quantity;
}

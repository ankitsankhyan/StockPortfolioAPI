package com.PortfolioApp.Stocks_portfolio.Entities;

import jakarta.persistence.*;

@Entity
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int stockId;
     private String name;
     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "profile_id",referencedColumnName = "id")
     private PriceEntity priceDetail;
}

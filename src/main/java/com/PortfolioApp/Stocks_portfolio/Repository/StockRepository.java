package com.PortfolioApp.Stocks_portfolio.Repository;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity,Integer> {
}

package com.PortfolioApp.Stocks_portfolio.Repository;

import com.PortfolioApp.Stocks_portfolio.Entities.StockHolding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockHoldingRepository extends JpaRepository<StockHolding, Integer> {
    Optional<StockHolding> findByStockIdAndUserId(Integer stockId, Integer userId);
}

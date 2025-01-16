package com.PortfolioApp.Stocks_portfolio.Repository;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity,Integer> {
    Optional<StockEntity> findByName(String name);
}

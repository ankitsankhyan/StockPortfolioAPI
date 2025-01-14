package com.PortfolioApp.Stocks_portfolio.Repository;

import com.PortfolioApp.Stocks_portfolio.Entities.ExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeEntity,Integer> {

}

package com.PortfolioApp.Stocks_portfolio.Repository;

import com.PortfolioApp.Stocks_portfolio.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}

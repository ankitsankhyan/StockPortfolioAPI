package com.PortfolioApp.Stocks_portfolio.Service;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import com.PortfolioApp.Stocks_portfolio.Entities.StockHolding;
import com.PortfolioApp.Stocks_portfolio.Repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetStocksService {
    final StockRepository stockRepository;
    GetStocksService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public List<StockEntity> getAllStocks(){
        return stockRepository.findAll();
    }
}

package com.PortfolioApp.Stocks_portfolio.Service;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import com.PortfolioApp.Stocks_portfolio.Repository.StockRepository;
import com.PortfolioApp.Stocks_portfolio.utils.CSVparser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StockUpdateService {
    final StockRepository stockRepository;
    StockUpdateService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public void addtoDB(MultipartFile file) throws IOException {
        CSVparser parser = new CSVparser();
        List<StockEntity> stocks = parser.parseCSVData(file);
        stocks.forEach(stock->{
            Optional<StockEntity> dbStock = stockRepository.findByName(stock.getName());
            if(dbStock.isEmpty()){
                stockRepository.save(stock);
            }else{
                stockRepository.delete(stockRepository.findByName(stock.getName()).get());
                stockRepository.save(dbStock.get());
            }
        });

    }
}

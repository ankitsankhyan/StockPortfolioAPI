package com.PortfolioApp.Stocks_portfolio.Service;

import com.PortfolioApp.Stocks_portfolio.Repository.StockRepository;
import com.PortfolioApp.Stocks_portfolio.utils.CSVparser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StockUpdateService {
    final StockRepository stockRepository;
    StockUpdateService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public void addtoDB(MultipartFile file) throws IOException {
        CSVparser parser = new CSVparser();
        stockRepository.saveAll(parser.parseCSVData(file));
    }
}

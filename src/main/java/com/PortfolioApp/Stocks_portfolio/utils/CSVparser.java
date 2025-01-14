package com.PortfolioApp.Stocks_portfolio.utils;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVparser {

    public List<StockEntity> parseCSVData(MultipartFile file) throws IOException{
        List<StockEntity> stocks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            boolean first = true;
            while ((line = reader.readLine()) != null) {
                String [] fields = line.split(",");
                if(first){
                    first = false;
                    continue;
                }
                String name = fields[3]; // SECURITY
                Double openPrice = Double.parseDouble(fields[5]); // OPEN_PRICE
                Double closePrice = Double.parseDouble(fields[8]); // CLOSE_PRICE
                Double highPrice = Double.parseDouble(fields[6]); // HIGH_PRICE
                Double lowPrice = Double.parseDouble(fields[7]); // LOW_PRICE
                Double settlementPrice = closePrice; // Assuming settlement price is close price

                // Build the Stock object
//                StockEntity stock = StockEntity.builder()
//                        .name(name)
//                        .openPrice(openPrice)
//                        .closePrice(closePrice)
//                        .highPrice(highPrice)
//                        .lowPrice(lowPrice)
//                        .settlementPrice(settlementPrice)
//                        .build();

                stocks.add(new StockEntity(name, openPrice, closePrice, highPrice, lowPrice, settlementPrice));

            }
            return stocks;

    }
}

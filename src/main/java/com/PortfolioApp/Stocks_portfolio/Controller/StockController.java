package com.PortfolioApp.Stocks_portfolio.Controller;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import com.PortfolioApp.Stocks_portfolio.Service.GetStocksService;
import com.PortfolioApp.Stocks_portfolio.Service.StockUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/stock")
public class StockController {
    private final StockUpdateService stockUpdateService;
    private final GetStocksService getStocksService;
    public StockController(StockUpdateService stockUpdateService, GetStocksService getStocksService) {
        this.stockUpdateService = stockUpdateService;
        this.getStocksService = getStocksService;
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<String> uploadData(@RequestParam(value = "file", required = false) MultipartFile file){
        if (file == null || file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty. Please upload a valid file.");
        }
        try{
            stockUpdateService.addtoDB(file);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded and processed successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the file: " + e.getMessage());
        }
    }
    @GetMapping(path = "/get", produces = "application/json")
    public List<StockEntity> getallStocks(){
        return getStocksService.getAllStocks();
    }
}

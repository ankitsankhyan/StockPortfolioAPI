package com.PortfolioApp.Stocks_portfolio.Controller;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import com.PortfolioApp.Stocks_portfolio.Service.GetStocksService;
import com.PortfolioApp.Stocks_portfolio.Service.StockUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Tag(name = "Stocks API")
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
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the file");
        }
    }
    @GetMapping(path = "/get/{id}", produces = "application/json")
    public ResponseEntity<StockEntity> getallStocks(@PathVariable("id") Integer id) {
        try {
            Optional<StockEntity> stocks = getStocksService.getStockById(id);
            if(stocks.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StockEntity());
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(stocks.get());
            }

        } catch (Exception e) {
            // Log the exception (using a logger is recommended)
            System.err.println("An error occurred while fetching stocks: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StockEntity());
        }
    }

}

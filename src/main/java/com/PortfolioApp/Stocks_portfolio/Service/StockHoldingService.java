package com.PortfolioApp.Stocks_portfolio.Service;

import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import com.PortfolioApp.Stocks_portfolio.Entities.StockHolding;
import com.PortfolioApp.Stocks_portfolio.Entities.TransactionType;
import com.PortfolioApp.Stocks_portfolio.Entities.UserEntity;
import com.PortfolioApp.Stocks_portfolio.Repository.StockHoldingRepository;
import com.PortfolioApp.Stocks_portfolio.Repository.StockRepository;
import com.PortfolioApp.Stocks_portfolio.Repository.UserRepository;
import com.PortfolioApp.Stocks_portfolio.dto.ExchangeDTO;
import com.PortfolioApp.Stocks_portfolio.dto.UserWithHoldingDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockHoldingService {
     private final StockHoldingRepository stockHoldingRepository;
     private final StockRepository stockRepository;
     private final UserRepository userRepository;
     StockHoldingService(StockHoldingRepository stockHoldingRepository, StockRepository stockRepository, UserRepository userRepository){
         this.stockHoldingRepository = stockHoldingRepository;
         this.stockRepository = stockRepository;
         this.userRepository = userRepository;
     }
     @Transactional
     public String StockholdingUser(ExchangeDTO exchangeDTO){
         int stockId = exchangeDTO.getStockId();
         int userId = exchangeDTO.getUserId();
         Double quantity = exchangeDTO.getQuantity();
         TransactionType type = exchangeDTO.getType();

         Optional<StockHolding> stockHoldingOptional = stockHoldingRepository.findByStockIdAndUserId(stockId, userId);
         Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
         UserEntity userEntity = userEntityOptional.get();


         // If stock holding doesn't exist for the user, create a new stock holding entry
         if(stockHoldingOptional.isEmpty()) {
             StockEntity stock = stockRepository.findById(stockId).orElseThrow(() -> new RuntimeException("Stock not found"));
             Double stockPrice = stock.getOpenPrice();
             Double totalPrice = quantity * stockPrice;

             StockHolding newStockHolding = new StockHolding();
             newStockHolding.setStock(stock);
             newStockHolding.setUser(userEntity);
             newStockHolding.setQuantity(quantity);
             newStockHolding.setPrice(totalPrice);

             stockHoldingRepository.save(newStockHolding);
             return "Stock holding created successfully!";
         } else {
             // If stock holding exists, update the existing entry
             StockHolding stockHolding = stockHoldingOptional.get();
             StockEntity stock = stockRepository.findById(stockId).orElseThrow(() -> new RuntimeException("Stock not found"));
             Double stockPrice = stock.getOpenPrice();

             if (type == TransactionType.BUY) {
                 stockHolding.setQuantity(stockHolding.getQuantity() + quantity);
                 stockHolding.setPrice(stockHolding.getQuantity() * stockPrice);
             } else if (type == TransactionType.SELL) {
                 if (stockHolding.getQuantity() < quantity) {
                     return "Insufficient stock to sell!";
                 }
                 stockHolding.setQuantity(stockHolding.getQuantity() - quantity);
                 stockHolding.setPrice(stockHolding.getQuantity() * stockPrice);
             }

             stockHoldingRepository.save(stockHolding);

         }

         return "Executed successfully";
     }

     }



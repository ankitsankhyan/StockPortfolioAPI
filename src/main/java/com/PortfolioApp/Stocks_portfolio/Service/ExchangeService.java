package com.PortfolioApp.Stocks_portfolio.Service;

import com.PortfolioApp.Stocks_portfolio.Entities.ExchangeEntity;
import com.PortfolioApp.Stocks_portfolio.Entities.StockEntity;
import com.PortfolioApp.Stocks_portfolio.Entities.TransactionType;
import com.PortfolioApp.Stocks_portfolio.Entities.UserEntity;
import com.PortfolioApp.Stocks_portfolio.Exception.RetryFailException;
import com.PortfolioApp.Stocks_portfolio.Repository.ExchangeRepository;
import com.PortfolioApp.Stocks_portfolio.Repository.StockHoldingRepository;
import com.PortfolioApp.Stocks_portfolio.Repository.StockRepository;
import com.PortfolioApp.Stocks_portfolio.Repository.UserRepository;
import com.PortfolioApp.Stocks_portfolio.dto.ExchangeDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExchangeService {
    public StockRepository stockRepository;
    public UserRepository userRepository;
    public ExchangeRepository exchangeRepository;
    public StockHoldingService stockHoldingService;
    ExchangeService(StockRepository stockRepository, UserRepository userRepository, StockHoldingService stockHoldingService){
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
        this.stockHoldingService = stockHoldingService;
    }
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    @Transactional
    public String exhangeStock(ExchangeDTO exchangeDTO){
        String s = stockHoldingService.StockholdingUser(exchangeDTO);
        System.out.println(s);
        int userId = exchangeDTO.getUserId();
         int stockId = exchangeDTO.getStockId();
         Double quantity = exchangeDTO.getQuantity();
         TransactionType type = exchangeDTO.getType();

        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isEmpty()){
            return "User not found with id " + userId;
        }

        UserEntity user = userEntityOptional.get();

        Optional<StockEntity> stockEntityOptional = stockRepository.findById(stockId);

        if(stockEntityOptional.isEmpty()){
            return "No stock is found with id " + stockId;
        }

        StockEntity stock = stockEntityOptional.get();
        Double price = stock.getOpenPrice();
        ExchangeEntity exchangeEntity = new ExchangeEntity();
        exchangeEntity.setStock(stock);
        exchangeEntity.setType(type);
        exchangeEntity.setUser(user);
        exchangeEntity.setQuantity(quantity);
        exchangeEntity.setPrice(quantity*price);

        return "Successfully executed";
    }
    @Recover
    public void exchangeStockRecoveryMethod() throws RetryFailException {
         throw new RetryFailException("Message can't be processed even after retry");
    }

}

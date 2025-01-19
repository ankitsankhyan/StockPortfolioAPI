package com.PortfolioApp.Stocks_portfolio.KafkaConsumer;

import com.PortfolioApp.Stocks_portfolio.Service.ExchangeService;
import com.PortfolioApp.Stocks_portfolio.dto.ExchangeDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TradeConsumer {
    public ExchangeService exchangeService;
    public TradeConsumer(ExchangeService exchangeService){
        this.exchangeService = exchangeService;
    }
    @KafkaListener(topics = "trades", groupId = "myGroup")
    public void getTrades(ExchangeDTO exchangeDTO){
         exchangeService.exhangeStock(exchangeDTO);
    }
}

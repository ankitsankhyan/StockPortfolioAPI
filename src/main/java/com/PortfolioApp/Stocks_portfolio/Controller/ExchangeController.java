package com.PortfolioApp.Stocks_portfolio.Controller;

import com.PortfolioApp.Stocks_portfolio.KafkaProducer.TradeProducer;
import com.PortfolioApp.Stocks_portfolio.Service.ExchangeService;
import com.PortfolioApp.Stocks_portfolio.dto.ExchangeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "trading")
@Tag(name = "Trade API")
public class ExchangeController {
    private ExchangeService exchangeService;
    private TradeProducer tradeProducer;
    ExchangeController(ExchangeService exchangeService,TradeProducer tradeProducer){
        this.exchangeService = exchangeService;
        this.tradeProducer = tradeProducer;
    }
    @PostMapping(path = "/exchange")
    public ResponseEntity<String> exchangeStock(@RequestBody ExchangeDTO exchangeDTO){
        try{
            if(exchangeDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
            }
            tradeProducer.sendData(exchangeDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Exchange is completed");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error" + e.getMessage());
        }

    }

}

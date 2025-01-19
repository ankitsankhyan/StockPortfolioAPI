package com.PortfolioApp.Stocks_portfolio.KafkaProducer;

import com.PortfolioApp.Stocks_portfolio.Config.KafkaConfig;
import com.PortfolioApp.Stocks_portfolio.dto.ExchangeDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;
@Service
public class TradeProducer {
    public KafkaTemplate<String, ExchangeDTO> kafkaTemplate;

    public TradeProducer(KafkaTemplate<String, ExchangeDTO> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendData(ExchangeDTO exchangeDTO){
        Message<ExchangeDTO>message = MessageBuilder
                                    .withPayload(exchangeDTO)
                                    .setHeader(KafkaHeaders.TOPIC, "trades")
                                    .build();
        kafkaTemplate.send(message);
    }
}

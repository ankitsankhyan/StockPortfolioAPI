package com.PortfolioApp.Stocks_portfolio.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
   @Bean
    public NewTopic transactionTopic(){
        return TopicBuilder.name("trades").partitions(1).replicas(2).build();
    }
}

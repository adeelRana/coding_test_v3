package com.smallworld;


import com.smallworld.services.TransactionDataFetcher;
import com.smallworld.services.impl.TransactionDataFetcherImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TransactionDataFetcher transactionDataFetcher() {
        return new TransactionDataFetcherImpl();
    }
}
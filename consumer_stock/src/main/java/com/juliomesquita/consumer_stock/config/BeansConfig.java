package com.juliomesquita.consumer_stock.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliomesquita.consumer_stock.exception_custom.ErrorStrategyCustom;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

@Configuration
public class BeansConfig {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public ErrorStrategyCustom errorStrategyCustom(){
        return new ErrorStrategyCustom();
    }

    @Bean
    public ErrorHandler errorHandler(){
        return new ConditionalRejectingErrorHandler(this.errorStrategyCustom());
    }
}

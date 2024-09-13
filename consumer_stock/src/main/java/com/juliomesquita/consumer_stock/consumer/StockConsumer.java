package com.juliomesquita.consumer_stock.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.juliomesquita.interfaces.RabbitMqConsumer;
import org.juliomesquita.rabbitMq_constants.RabbitMqConstants;
import org.juliomesquita.requests.StockRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
class StockConsumer implements RabbitMqConsumer {
    private final ObjectMapper objectMapper;

    public StockConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @RabbitListener(queues = RabbitMqConstants.QUEUE_STOCK)
    public void consumer(String msg) throws JsonProcessingException {
        StockRequest request = this.objectMapper.readValue(msg, StockRequest.class);
        System.out.println(request.codProduct());
        System.out.println(request.qnt());
        System.out.println("<-----xxxxx----->");
        throw new IllegalArgumentException("Forçado error.");
    }
}

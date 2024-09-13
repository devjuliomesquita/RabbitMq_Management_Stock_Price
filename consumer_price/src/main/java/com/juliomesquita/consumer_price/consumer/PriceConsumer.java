package com.juliomesquita.consumer_price.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.juliomesquita.interfaces.RabbitMqConsumer;
import org.juliomesquita.rabbitMq_constants.RabbitMqConstants;
import org.juliomesquita.requests.PriceRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
class PriceConsumer implements RabbitMqConsumer {
    private final ObjectMapper objectMapper;

    public PriceConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @RabbitListener(queues = RabbitMqConstants.QUEUE_PRICE)
    public void consumer(String msg) throws JsonProcessingException {
        PriceRequest priceRequest = this.objectMapper.readValue(msg, PriceRequest.class);
        System.out.println(priceRequest.codProduct());
        System.out.println(priceRequest.price());
        System.out.println("<-----xxxxx----->");
    }
}

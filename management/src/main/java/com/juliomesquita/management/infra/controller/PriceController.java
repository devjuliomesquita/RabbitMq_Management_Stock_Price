package com.juliomesquita.management.infra.controller;


import com.juliomesquita.management.infra.services.RabbitMqService;
import org.juliomesquita.rabbitMq_constants.RabbitMqConstants;
import org.juliomesquita.requests.PriceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "price")
public class PriceController {
    private final RabbitMqService rabbitMqService;

    public PriceController(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @PutMapping
    public ResponseEntity<?> alterPrice(@RequestBody PriceRequest request){
        this.rabbitMqService.sendMessage(RabbitMqConstants.QUEUE_PRICE, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

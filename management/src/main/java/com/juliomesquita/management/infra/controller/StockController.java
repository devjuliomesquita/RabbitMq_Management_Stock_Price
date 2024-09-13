package com.juliomesquita.management.infra.controller;


import com.juliomesquita.management.infra.services.RabbitMqService;
import org.juliomesquita.rabbitMq_constants.RabbitMqConstants;
import org.juliomesquita.requests.StockRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "stock")
public class StockController {

    private final RabbitMqService rabbitMqService;

    public StockController(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @PutMapping
    private ResponseEntity<?> alterStock(@RequestBody StockRequest request){
        this.rabbitMqService.sendMessage(RabbitMqConstants.QUEUE_STOCK, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package com.juliomesquita.management.infra.connection;

import org.juliomesquita.rabbitMq_constants.RabbitMqConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConnection {
    private static final String EXCHANGE_NAME = "amq.direct";

    private final AmqpAdmin amqpAdmin;

    public RabbitMqConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
        this.add();
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    private void add() {
        Queue queuePrice = this.queue(RabbitMqConstants.QUEUE_PRICE);
        Queue queueStock = this.queue(RabbitMqConstants.QUEUE_STOCK);

        DirectExchange directExchange = this.directExchange();

        Binding bindingPrice = this.binding(queuePrice, directExchange);
        Binding bindingStock = this.binding(queueStock, directExchange);

        this.amqpAdmin.declareQueue(queuePrice);
        this.amqpAdmin.declareQueue(queueStock);

        this.amqpAdmin.declareExchange(directExchange);

        this.amqpAdmin.declareBinding(bindingPrice);
        this.amqpAdmin.declareBinding(bindingStock);
    }
}

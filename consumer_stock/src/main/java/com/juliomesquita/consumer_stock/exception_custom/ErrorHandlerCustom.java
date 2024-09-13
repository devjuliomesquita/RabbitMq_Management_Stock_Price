package com.juliomesquita.consumer_stock.exception_custom;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class ErrorHandlerCustom implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        String consumerQueue = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
        String msg = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());

        System.out.println(consumerQueue);
        System.out.println(msg);
        System.out.println(t.getCause().getMessage());

        // Mais opçoes seria guardar em um banco de logs.

        throw new AmqpRejectAndDontRequeueException("Msg não retornada a fila.");
    }
}

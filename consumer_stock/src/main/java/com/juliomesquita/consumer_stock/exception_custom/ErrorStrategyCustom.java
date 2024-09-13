package com.juliomesquita.consumer_stock.exception_custom;

import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;

public class ErrorStrategyCustom extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

    @Override
    public boolean isFatal(Throwable t) {
        String msg = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        System.out.println(msg);
        return t.getCause() instanceof IllegalArgumentException;
    }
}

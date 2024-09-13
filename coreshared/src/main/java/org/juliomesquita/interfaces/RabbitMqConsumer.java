package org.juliomesquita.interfaces;

public interface RabbitMqConsumer {
    void consumer(String msg) throws Exception;
}

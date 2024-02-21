package ru.t1academy.messageBroker.broker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageBrokerImpl<T> implements MessageBroker<T> {
    private final BlockingQueue<T> queue = new LinkedBlockingQueue<>();

    @Override
    public void publish(T message) {
        queue.add(message);
    }

    @Override
    public T poll() {
        return queue.poll();
    }

}

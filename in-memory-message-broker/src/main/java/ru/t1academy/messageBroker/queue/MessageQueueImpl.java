package ru.t1academy.messageBroker.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueueImpl<T> implements MessageQueue<T> {
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

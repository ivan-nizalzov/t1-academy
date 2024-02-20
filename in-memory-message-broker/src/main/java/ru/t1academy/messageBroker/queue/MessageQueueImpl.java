package ru.t1academy.messageBroker.queue;

import ru.t1academy.messageBroker.subscriber.MessageSubscriber;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueueImpl implements MessageQueue, Runnable {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @Override
    public boolean publish(String message) {
        return queue.offer(message);
    }

    @Override
    public String poll() {
        return queue.poll();
    }

    @Override
    public void subscribe(MessageSubscriber messageSubscriber) {
        //TODO
    }

    @Override
    public void run() {
        //TODO
    }

}

package ru.t1academy.messageBroker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void publish(String message) {

    }

    public String poll() {
        return queue.poll();
    }

}

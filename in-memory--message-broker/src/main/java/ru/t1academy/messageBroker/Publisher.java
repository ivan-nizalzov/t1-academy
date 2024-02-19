package ru.t1academy.messageBroker;

public class Publisher {
    private final MessageQueue queue;

    public Publisher(MessageQueue queue) {
        this.queue = queue;
    }

    public void publishMessage(String message) {
        queue.publish(message);
    }

}

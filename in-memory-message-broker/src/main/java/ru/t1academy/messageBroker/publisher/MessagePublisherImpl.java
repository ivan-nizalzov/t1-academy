package ru.t1academy.messageBroker.publisher;

import ru.t1academy.messageBroker.queue.MessageQueue;

public class MessagePublisherImpl implements MessagePublisher {
    private final MessageQueue queue;

    public MessagePublisherImpl(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void publishMessage(String message) {
        queue.publish(message);
    }

}

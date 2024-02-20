package ru.t1academy.messageBroker.publisher;

import ru.t1academy.messageBroker.queue.MessageQueue;

public class MessagePublisherImpl<T> implements MessagePublisher<T> {
    private final MessageQueue<T> queue;

    public MessagePublisherImpl(MessageQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public void publishMessage(T message) {
        queue.publish(message);
    }

}

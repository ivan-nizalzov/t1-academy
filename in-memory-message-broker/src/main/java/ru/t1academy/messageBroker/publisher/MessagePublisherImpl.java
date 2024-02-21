package ru.t1academy.messageBroker.publisher;

import ru.t1academy.messageBroker.broker.MessageBroker;

public class MessagePublisherImpl<T> implements MessagePublisher<T> {
    private final MessageBroker<T> messageBroker;

    public MessagePublisherImpl(MessageBroker<T> messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public void publish(T message) {
        messageBroker.publish(message);
    }

}

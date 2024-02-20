package ru.t1academy.messageBroker.publisher;

public interface MessagePublisher<T> {
    void publishMessage(T message);

}

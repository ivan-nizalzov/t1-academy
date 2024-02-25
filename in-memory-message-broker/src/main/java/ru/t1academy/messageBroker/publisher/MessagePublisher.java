package ru.t1academy.messageBroker.publisher;

public interface MessagePublisher<T> {
    void publish(T message);

}

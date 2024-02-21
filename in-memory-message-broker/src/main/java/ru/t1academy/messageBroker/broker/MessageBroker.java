package ru.t1academy.messageBroker.broker;

public interface MessageBroker<T> {
    void publish(T message);

    T poll();

}

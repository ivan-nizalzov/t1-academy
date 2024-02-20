package ru.t1academy.messageBroker.queue;

public interface MessageQueue<T> {
    void publish(T message);

    T poll();

}

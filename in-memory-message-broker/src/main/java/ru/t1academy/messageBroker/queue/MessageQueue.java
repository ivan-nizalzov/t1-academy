package ru.t1academy.messageBroker.queue;

import ru.t1academy.messageBroker.subscriber.MessageSubscriber;

public interface MessageQueue {
    boolean publish(String message);
    String poll();

    void subscribe(MessageSubscriber messageSubscriber);

}

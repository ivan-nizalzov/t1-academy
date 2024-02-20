package ru.t1academy.messageBroker.subscriber;

public interface MessageSubscriber {
    void subscribe();
    boolean requestMessage(String message);

}

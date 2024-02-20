package ru.t1academy.messageBroker.subscriber;

import ru.t1academy.messageBroker.queue.MessageQueue;

public class MessageSubscriberImpl implements MessageSubscriber {
    private final MessageQueue queue;

    public MessageSubscriberImpl(MessageQueue queue) {
        this.queue = queue;
    }



   /* @Override
    public void run() {
        while (true) {
            String message = queue.poll();
            if (message != null) {
                System.out.println("Received message: " + message);
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Subscriber interrupted.");
                    break;
                }
            }
        }
    }*/

}

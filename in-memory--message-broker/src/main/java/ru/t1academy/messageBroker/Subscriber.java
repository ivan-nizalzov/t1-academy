package ru.t1academy.messageBroker;

public class Subscriber {
    private final MessageQueue queue;

    public Subscriber(MessageQueue queue) {
        this.queue = queue;
    }

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
    }

}

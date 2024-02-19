package ru.t1academy.messageBroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InMemoryMessageBroker {

    public static void main(String[] args) {
        SpringApplication.run(InMemoryMessageBroker.class, args);
    }

}

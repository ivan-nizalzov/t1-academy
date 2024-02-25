package ru.t1academy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.t1academy.messageBroker.publisher.MessagePublisher;
import ru.t1academy.messageBroker.publisher.MessagePublisherImpl;
import ru.t1academy.messageBroker.broker.MessageBroker;
import ru.t1academy.messageBroker.broker.MessageBrokerImpl;
import ru.t1academy.model.SupportPhrase;

@Configuration
@ComponentScan("ru.t1academy")
public class SupportConfig {
    @Bean
    public MessageBroker<SupportPhrase> messageBroker() {
        return new MessageBrokerImpl<>();
    }

    @Bean
    public MessagePublisher<SupportPhrase> messagePublisher(MessageBroker<SupportPhrase> messageBroker) {
        return new MessagePublisherImpl<>(messageBroker);
    }

}

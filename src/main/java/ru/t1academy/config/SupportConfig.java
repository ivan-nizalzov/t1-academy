package ru.t1academy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.t1academy.controller.SupportController;
import ru.t1academy.messageBroker.publisher.MessagePublisher;
import ru.t1academy.messageBroker.publisher.MessagePublisherImpl;
import ru.t1academy.messageBroker.queue.MessageQueue;
import ru.t1academy.messageBroker.queue.MessageQueueImpl;
import ru.t1academy.subscriber.MessageSubscriber;
import ru.t1academy.subscriber.MessageSubscriberImpl;
import ru.t1academy.repository.SupportRepository;
import ru.t1academy.repository.SupportRepositoryImpl;
import ru.t1academy.service.SupportService;
import ru.t1academy.service.SupportServiceImpl;

@Configuration
@ComponentScan("ru.t1academy")
public class SupportConfig {
    @Bean
    public SupportRepository supportRepository() {
        return new SupportRepositoryImpl();
    }

    @Bean
    public SupportService supportService(SupportRepository supportRepository) {
        return new SupportServiceImpl(supportRepository);
    }

    @Bean
    public SupportController supportController(SupportService supportService) {
        return new SupportController(supportService);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public MessageQueue messageQueue() {
        return new MessageQueueImpl();
    }

    @Bean
    public MessagePublisher publisher(MessageQueue messageQueue) {
        return new MessagePublisherImpl(messageQueue);
    }

    @Bean
    public MessageSubscriber subscriber(MessageQueue messageQueue, SupportService supportService) {
        return new MessageSubscriberImpl(messageQueue, supportService);
    }

}

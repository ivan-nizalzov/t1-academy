package ru.t1academy.helpservice.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.t1academy.helpservice.broker.KafkaConsumer;
import ru.t1academy.helpservice.repository.SupportRepository;

@Configuration
public class KafkaConfig {
    @Bean
    @ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
    public KafkaConsumer consumer(SupportRepository supportRepository) {
        return new KafkaConsumer(supportRepository);
    }

}

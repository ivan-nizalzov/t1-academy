package ru.t1academy.helpservice.broker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.t1academy.helpservice.config.KafkaConfigProperties;
import ru.t1academy.helpservice.model.SupportPhrase;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, SupportPhrase> kafkaTemplate;
    private final KafkaConfigProperties kafkaConfigProperties;

    public void produce(SupportPhrase supportPhrase) {
        kafkaTemplate.executeInTransaction(kafkaTemplate -> {
            CompletableFuture<SendResult<String, SupportPhrase>> sendFutureResult = kafkaTemplate.send(
                    kafkaConfigProperties.getTopicName(), supportPhrase.content(), supportPhrase
            );
            sendFutureResult.whenComplete(((sendResult, throwable) -> {
                if (throwable != null) {
                    log.error("An error occurred while sending supportPhrase to Kafka", throwable);
                }
            }));
            return sendFutureResult;
        });
    }

}

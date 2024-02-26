package ru.t1academy.helpservice.broker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import ru.t1academy.helpservice.model.SupportPhrase;
import ru.t1academy.helpservice.repository.SupportRepository;

@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final SupportRepository supportRepository;

    @Transactional
    @KafkaListener(topics = "${kafka.topic-name}",
            groupId = "${spring.kafka.consumer.group-id}",
            properties = {"spring.json.value.default.type=ru.t1academy.model.SupportPhrase"})
    public SupportPhrase consume(SupportPhrase supportPhrase) {
        supportRepository.save(supportPhrase);
        return supportPhrase;
    }

}

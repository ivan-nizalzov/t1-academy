package ru.t1academy.helpservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.t1academy.helpservice.broker.KafkaProducer;
import ru.t1academy.helpservice.config.KafkaConfigProperties;
import ru.t1academy.helpservice.dto.SupportPhraseMapper;
import ru.t1academy.helpservice.exception.AlreadyExistException;
import ru.t1academy.helpservice.model.SupportPhrase;
import ru.t1academy.helpservice.repository.SupportRepository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
    private final SupportRepository supportRepository;
    private final SupportPhraseMapper supportPhraseMapper;
    private final KafkaConfigProperties kafkaConfigProperties;
    private final KafkaProducer kafkaProducer;

    @Override
    public void addSupportPhrase(SupportPhrase supportPhrase) {
        if (kafkaConfigProperties.getIsEnabled()) {
            kafkaProducer.produce(supportPhrase);
        } else {
            if (!supportRepository.isPhraseAlreadyAdded(supportPhraseMapper.toString(supportPhrase))) {
                supportRepository.save(supportPhrase);
            } else {
                throw new AlreadyExistException("The support message: " + supportPhrase.content() + " already exist!");
            }
        }
    }

    @Override
    public SupportPhrase getRandomSupportPhrase() {
        List<String> allPhrases = supportRepository.getAllSupportPhrases();
        Collections.shuffle(allPhrases);
        log.info("Get a random support phrase.");
        return allPhrases.stream()
                .findFirst()
                .map(supportPhraseMapper::toSupportPhrase)
                .orElseThrow();
    }

}

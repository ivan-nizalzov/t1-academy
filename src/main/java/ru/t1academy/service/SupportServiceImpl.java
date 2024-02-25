package ru.t1academy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.t1academy.dto.SupportPhraseMapper;
import ru.t1academy.messageBroker.annotation.Subscriber;
import ru.t1academy.messageBroker.broker.MessageBroker;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.repository.SupportRepository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
    private final SupportRepository supportRepository;
    private final SupportPhraseMapper supportPhraseMapper;
    private final MessageBroker<SupportPhrase> messageBroker;

    @Subscriber
    public SupportPhrase subscribe() {
        SupportPhrase supportPhrase = messageBroker.poll();
        if (supportPhrase != null) {
            supportRepository.save(supportPhrase);
        }
        return supportPhrase;
    }

    @Override
    public SupportPhrase getRandomSupportPhrase() {
        List<String> allPhrases = supportRepository.getAllSupportPhrases();
        Collections.shuffle(allPhrases);
        log.info("Get a random support phrase.");
        return allPhrases.stream()
                .findFirst()
                .map(supportPhraseMapper::stringToSupportPhrase)
                .orElseThrow();
    }

}

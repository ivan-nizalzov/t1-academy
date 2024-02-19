package ru.t1academy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.repository.SupportRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
    private final SupportRepository supportRepository;

    @Override
    public SupportPhrase getRandomSupportPhrase() {
        List<String> allPhrases = supportRepository.getAllSupportPhrases();
        Collections.shuffle(allPhrases);

        log.info("Get a random support phrase.");

        return allPhrases.stream()
                .findFirst()
                .map(this::makeSupportPhrase)
                .orElseThrow();
    }

    @Override
    public void addSupportPhrase(SupportPhrase supportPhrase) throws IOException {
        if (supportPhrase == null) {
            throw new IOException("Support words cannot be empty!");
        }

        if (supportRepository.isPhraseAlreadyAdded(supportPhrase.content())) {
            throw new IOException("The support words have already added!");
        } else {
            supportRepository.addSupportPhrase(supportPhrase.content());
            log.info("Added new support phrase: {}", supportPhrase.content());
        }

    }

    private SupportPhrase makeSupportPhrase(String words) {
        return new SupportPhrase(words);
    }

}

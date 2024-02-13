package ru.t1academy.service;

import ru.t1academy.context.annotation.stereotype.Service;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.repository.SupportRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class SupportServiceImpl implements SupportService {
    public SupportRepository supportRepository;

    public SupportServiceImpl(SupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    @Override
    public SupportPhrase getRandomSupportPhrase() {
        List<String> allPhrases = supportRepository.getAllSupportPhrases();
        Collections.shuffle(allPhrases);

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
        }

    }

    private SupportPhrase makeSupportPhrase(String words) {
        return new SupportPhrase(words);
    }

}

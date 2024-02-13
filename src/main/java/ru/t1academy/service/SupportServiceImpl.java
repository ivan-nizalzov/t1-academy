package ru.t1academy.service;

import ru.t1academy.context.annotation.factory.Autowired;
import ru.t1academy.context.annotation.stereotype.Service;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.repository.SupportRepository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SupportServiceImpl implements SupportService {
    @Autowired
    private SupportRepository supportRepository;
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public SupportServiceImpl() {

    }

    public SupportServiceImpl(SupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    @Override
    public String getRandomSupportResponse() {
        List<SupportPhrase> allPhrases = supportRepository.getAllSupportPhrases();
        Collections.shuffle(allPhrases);

        return allPhrases.stream()
                .findFirst()
                .map(SupportPhrase::getWords)
                .orElse("No support words has been added!");
    }

    @Override
    public String addSupportWords(String supportWords) {
        String response;

        if (supportWords.isEmpty()) {
            response = "Support words cannot be empty!";
            return response;
        }

        if (supportRepository.isPhraseAlreadyAdded(supportWords)) {
            StringBuilder sb = new StringBuilder();
            sb.append("The support words have already added: ");
            sb.append(supportWords);
            response = sb.toString();
        } else {
            supportRepository.addSupportPhrase(makePhrase(supportWords));
            response = "Support words are successful added!";
        }

        return response;
    }

    @Override
    public List<SupportPhrase> getAllSupportPhrases() {
        return supportRepository.getAllSupportPhrases();
    }


    private SupportPhrase makePhrase(String words) {
        Integer id = idCounter.getAndIncrement();
        return new SupportPhrase(id, words);
    }
}

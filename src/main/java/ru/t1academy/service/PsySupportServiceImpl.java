package ru.t1academy.service;

import ru.t1academy.context.annotation.factory.Autowired;
import ru.t1academy.context.annotation.stereotype.Service;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.repository.PsySupportRepository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PsySupportServiceImpl implements PsySupportService {
    @Autowired
    private PsySupportRepository supportRepository;
    private final AtomicInteger idCounter = new AtomicInteger(1);


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

    private SupportPhrase makePhrase(String words) {
        Integer id = idCounter.getAndIncrement();
        return new SupportPhrase(id, words);
    }
}

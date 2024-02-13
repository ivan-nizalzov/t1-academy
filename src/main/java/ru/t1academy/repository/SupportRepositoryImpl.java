package ru.t1academy.repository;

import ru.t1academy.context.annotation.stereotype.Repository;
import ru.t1academy.model.SupportPhrase;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SupportRepositoryImpl implements SupportRepository {
    private final Map<String, SupportPhrase> psychologySupportMap = new ConcurrentHashMap<>();

    @Override
    public SupportPhrase getSupportPhrase(String key) {
        return psychologySupportMap.get(key);
    }

    @Override
    public void addSupportPhrase(SupportPhrase phrase) {
        psychologySupportMap.put(phrase.getWords(), phrase);
    }

    @Override
    public List<SupportPhrase> getAllSupportPhrases() {
        return psychologySupportMap.values().stream()
                .toList();
    }

    @Override
    public boolean isPhraseAlreadyAdded(String words) {
        return psychologySupportMap.containsKey(words);
    }
}

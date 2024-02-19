package ru.t1academy.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class SupportRepositoryImpl implements SupportRepository {
    private final Map<String, Integer> psychologySupportMap = new ConcurrentHashMap<>();

    @Override
    public void addSupportPhrase(String supportPhrase) {
        psychologySupportMap.put(supportPhrase, 1);
    }

    @Override
    public List<String> getAllSupportPhrases() {
        return psychologySupportMap.keySet().stream()
                .toList();
    }

    @Override
    public boolean isPhraseAlreadyAdded(String words) {
        return psychologySupportMap.containsKey(words);
    }

}

package ru.t1academy.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t1academy.model.SupportPhrase;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class SupportRepository {
    private static final Map<String, Integer> psychologySupportMap = new ConcurrentHashMap<>();

    static {
        psychologySupportMap.put("A random support phrase", 1);
    }

    public List<String> getAllSupportPhrases() {
        return psychologySupportMap.keySet().stream()
                .toList();
    }

    public boolean isPhraseAlreadyAdded(String words) {
        return psychologySupportMap.containsKey(words);
    }

    public void save(SupportPhrase message) {
        psychologySupportMap.put(message.content(), 1);
    }

}

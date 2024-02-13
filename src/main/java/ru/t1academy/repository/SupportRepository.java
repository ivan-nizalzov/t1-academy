package ru.t1academy.repository;

import ru.t1academy.context.annotation.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRepository {
    void addSupportPhrase(String SupportPhrase);
    List<String> getAllSupportPhrases();
    boolean isPhraseAlreadyAdded(String words);

}

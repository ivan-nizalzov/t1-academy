package ru.t1academy.repository;

import java.util.List;

public interface SupportRepository {
    void addSupportPhrase(String SupportPhrase);
    List<String> getAllSupportPhrases();
    boolean isPhraseAlreadyAdded(String words);

}

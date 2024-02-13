package ru.t1academy.repository;

import ru.t1academy.model.SupportPhrase;

import java.util.List;

public interface SupportRepository {
    SupportPhrase getSupportPhrase(String key);

    void addSupportPhrase(SupportPhrase words);

    List<SupportPhrase> getAllSupportPhrases();

    boolean isPhraseAlreadyAdded(String words);
}

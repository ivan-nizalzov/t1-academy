package ru.t1academy.service;

import ru.t1academy.model.SupportPhrase;

import java.util.List;

public interface SupportService {

    String getRandomSupportResponse();

    String addSupportWords(String phrase);

    List<SupportPhrase> getAllSupportPhrases();

}

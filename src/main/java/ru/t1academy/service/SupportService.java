package ru.t1academy.service;

import ru.t1academy.model.SupportPhrase;

public interface SupportService {
    void addSupportPhrase(SupportPhrase supportPhrase);

    SupportPhrase getRandomSupportPhrase();

}

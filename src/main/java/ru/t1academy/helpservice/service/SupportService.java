package ru.t1academy.helpservice.service;

import ru.t1academy.helpservice.model.SupportPhrase;

public interface SupportService {
    void addSupportPhrase(SupportPhrase supportPhrase);

    SupportPhrase getRandomSupportPhrase();

}

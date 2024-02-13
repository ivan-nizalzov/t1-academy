package ru.t1academy.service;

import ru.t1academy.context.annotation.factory.Logged;
import ru.t1academy.context.annotation.stereotype.Service;
import ru.t1academy.model.SupportPhrase;

import java.io.IOException;
import java.util.List;

@Service
public interface SupportService {
    @Logged
    SupportPhrase getRandomSupportPhrase();
    @Logged
    void addSupportPhrase(SupportPhrase supportPhrase) throws IOException;

}

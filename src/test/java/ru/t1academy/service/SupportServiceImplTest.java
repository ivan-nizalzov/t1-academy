package ru.t1academy.service;

import org.junit.jupiter.api.Test;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.repository.SupportRepository;
import ru.t1academy.repository.SupportRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupportServiceImplTest {
    private static final SupportRepository supportRepository = new SupportRepositoryImpl();
    private static final SupportService supportService = new SupportServiceImpl(supportRepository);

    @Test
    public void shouldAddNewSupportPhrase() {
        List<SupportPhrase> phraseList = new ArrayList<>();
        String testWords = "Test phrase";
        supportService.addSupportWords(testWords);
        phraseList.addAll(supportService.getAllSupportPhrases());

        assertEquals(1, phraseList.size());
        assertEquals(phraseList.get(0).getWords(), testWords);
    }

}
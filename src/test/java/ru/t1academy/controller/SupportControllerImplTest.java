package ru.t1academy.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.service.SupportService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

class SupportControllerImplTest {
    private static final SupportService supportService = Mockito.mock(SupportService.class);
    private final SupportController supportController = new SupportControllerImpl(supportService);

    @Test
    public void shouldGetPhraseFromList() {
        String resultPhrase = "Test words";
        SupportPhrase testPhrase = new SupportPhrase(resultPhrase);
        Mockito.when(supportService.getRandomSupportPhrase()).thenReturn(testPhrase);
        String phrase = supportController.getRandomSupportPhrase().content();
        assertEquals(resultPhrase, phrase);
        Mockito.verify(supportService).getRandomSupportPhrase();
    }

    @Test
    public void shouldPostPhraseToList() throws IOException {
        String newPhrase = "New Test Words";
        SupportPhrase testPhrase = new SupportPhrase(newPhrase);
        supportController.addSupportPhrase(testPhrase);
        Mockito.verify(supportService).addSupportPhrase(testPhrase);
    }

}
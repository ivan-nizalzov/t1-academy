package ru.t1academy.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.t1academy.service.SupportService;

import static org.junit.Assert.assertEquals;

class SupportControllerImplTest {
    private static final SupportService supportService = Mockito.mock(SupportService.class);
    private final SupportController supportController = new SupportControllerImpl(supportService);

    @Test
    public void shouldGetPhraseFromList() {
        String resultPhrase = "Test words";
        Mockito.when(supportService.getRandomSupportResponse()).thenReturn(resultPhrase);
        String phrase = supportController.getRandomSupportPhrase();
        assertEquals(resultPhrase, phrase);
        Mockito.verify(supportService).getRandomSupportResponse();
    }

    @Test
    public void shouldPostPhraseToList() {
        String newPhrase = "New Test Words";
        supportController.addSupportPhrase(newPhrase);
        Mockito.verify(supportService).addSupportWords(newPhrase);
    }

}
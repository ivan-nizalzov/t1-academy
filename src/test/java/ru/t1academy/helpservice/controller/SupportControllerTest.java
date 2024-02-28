package ru.t1academy.helpservice.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.t1academy.helpservice.model.SupportPhrase;
import ru.t1academy.helpservice.service.SupportService;

import static org.junit.jupiter.api.Assertions.*;

class SupportControllerTest {
    private static final SupportService supportService = Mockito.mock(SupportService.class);
    private static final SupportController supportController = new SupportController(supportService);

    @Test
    void shouldReturnSupportPhraseForGetRequest() {
        SupportPhrase resultPhrase = new SupportPhrase("phrase");
        Mockito.when(supportService.getRandomSupportPhrase()).thenReturn(resultPhrase);
        SupportPhrase phrase = supportController.getRandomSupportPhrase();
        assertEquals(resultPhrase, phrase);
        Mockito.verify(supportService).getRandomSupportPhrase();
    }

    @Test
    void shouldSaveSupportPhraseForPostRequest() {
        SupportPhrase newPhrase = new SupportPhrase("new phrase");
        supportController.addSupportPhrase(newPhrase);
        Mockito.verify(supportService).addSupportPhrase(newPhrase);
    }
}
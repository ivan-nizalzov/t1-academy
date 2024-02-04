package ru.t1_academy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.t1_academy.repository.PsychologySupportRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PsychologySupportServiceImplTests {
    @Mock
    PsychologySupportRepository mockRepository;
    @Mock
    Map<String, Integer> mockMap;

    @BeforeEach
    public void setup() {
        mockRepository = Mockito.mock(PsychologySupportRepository.class);
        mockMap = new HashMap<>();
        mockRepository.setPsychologySupportMap(mockMap);
    }

    @Test
    void testGetRandomSupportResponse_WithAddedStrings() {
        // Arrange
        String expectedResponse = "Some support words";
        mockMap.put(expectedResponse, 1);
        when(mockRepository.getPsychologySupportMap()).thenReturn(mockMap);
        PsychologySupportServiceImpl supportService = new PsychologySupportServiceImpl(mockRepository);

        // Act
        String actualResponse = supportService.getRandomSupportResponse();

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetRandomSupportResponse_WithNoStringsAdded() {
        // Arrange
        String expectedResponse = "No support words has been added!";
        when(mockRepository.getPsychologySupportMap()).thenReturn(Collections.emptyMap());
        PsychologySupportServiceImpl supportService = new PsychologySupportServiceImpl(mockRepository);

        // Act
        String actualResponse = supportService.getRandomSupportResponse();

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

   /* @Test
    void testAddSupportWords_WithNewWord() {
        // Arrange
        String supportWords = "New support words";
        String expectedResponse = "Support words are successfully added!";
        when(mockRepository.getPsychologySupportMap()).thenReturn(mockMap);
        //when(mockRepository.psychologySupportMap()).thenReturn(mockMap);
        PsychologySupportServiceImpl supportService = new PsychologySupportServiceImpl(mockRepository);
        String tst = "hhh";
        // Act
        String actualResponse = supportService.addSupportWords(supportWords);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }*/

    @Test
    void testAddSupportWords_WithEmptyWord() {
        // Arrange
        String supportWords = "";
        String expectedResponse = "Support words cannot be empty!";
        PsychologySupportServiceImpl supportService = new PsychologySupportServiceImpl(mockRepository);

        // Act
        String actualResponse = supportService.addSupportWords(supportWords);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testAddSupportWords_WithDuplicateWord() {
        // Arrange
        String supportWords = "Existing words";
        String expectedResponse = "The support words have already added: Existing words";

        mockMap.put(supportWords, 1);
        when(mockRepository.getPsychologySupportMap()).thenReturn(mockMap);
        PsychologySupportServiceImpl supportService = new PsychologySupportServiceImpl(mockRepository);

        // Act
        String actualResponse = supportService.addSupportWords(supportWords);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

}
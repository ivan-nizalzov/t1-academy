package ru.t1_academy.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RequiredArgsConstructor
public class PsychologySupportRepository {
    // This field must be final and private, but in that case my unit-tests don't work
    public Map<String, Integer> psychologySupportMap;

    public Map<String, Integer> getPsychologySupportMap() {
        return psychologySupportMap;
    }

    // Setter only for mock-testing. This class will be replaced with a DB interface
    public void setPsychologySupportMap(Map<String, Integer> psychologySupportMap) {
        this.psychologySupportMap = psychologySupportMap;
    }
}

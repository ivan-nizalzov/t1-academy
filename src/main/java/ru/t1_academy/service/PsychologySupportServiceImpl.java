package ru.t1_academy.service;

import ru.t1_academy.repository.PsychologySupportRepository;

import java.util.List;
import java.util.Random;

public class PsychologySupportServiceImpl implements PsychologySupportService {
    private final PsychologySupportRepository supportRepository = new PsychologySupportRepository();

    @Override
    public String getRandomSupportResponse() {
        List<String> allPhrase = List.copyOf(supportRepository.getPsychologySupportMap().keySet());
        Random random = new Random();

        if (allPhrase.size() == 0) {
            return "No support words has been added!";
        } else {
            int randomIndex = random.nextInt(allPhrase.size());
            return allPhrase.get(randomIndex);
        }

    }

    @Override
    public String addSupportWords(String supportWords) {
        String response;
        int phraseCount = 1;

        if (supportWords.isEmpty()) {
            response = "Support words cannot be empty!";
            return response;
        }

        if (supportRepository.getPsychologySupportMap().containsKey(supportWords)) {
            StringBuilder sb = new StringBuilder();
            sb.append("The support words have already added: ");
            sb.append(supportWords);
            response = sb.toString();
        } else {
            supportRepository.psychologySupportMap.put(supportWords, phraseCount);
            response = "Support words are successful added!";
        }

        return response;
    }

}

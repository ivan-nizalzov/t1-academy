package ru.t1_academy.service;

import lombok.AllArgsConstructor;
import ru.t1_academy.repository.PsychologySupportRepository;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class PsychologySupportServiceImpl implements PsychologySupportService {
    private final PsychologySupportRepository supportRepository;

    @Override
    public String getRandomSupportResponse() {
        List<String> allPhrase = List.copyOf(supportRepository.psychologySupportMap.keySet());
        Random random = new Random();
        int randomIndex = random.nextInt(allPhrase.size());

        return allPhrase.get(randomIndex);
    }

    @Override
    public String addSupportWords(String supportWords) {
        String response;
        int phraseCount = 1;

        if (supportWords.isEmpty()) {
            response = "Support words cannot be empty!";

            return response;
        }

        if (supportRepository.psychologySupportMap.containsKey(supportWords)) {
            StringBuilder sb = new StringBuilder();
            sb.append("The support words have already added: ");
            sb.append(supportWords);
            response = sb.toString();

            return response;

        } else {
            supportRepository.psychologySupportMap.put(supportWords, phraseCount);
            response = "Support words are successful added!";

            return response;
        }

    }

}

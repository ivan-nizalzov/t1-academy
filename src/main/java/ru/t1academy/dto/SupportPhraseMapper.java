package ru.t1academy.dto;

import org.springframework.stereotype.Component;
import ru.t1academy.model.SupportPhrase;

@Component
public class SupportPhraseMapper {
    public SupportPhrase toSupportPhrase(String words) {
        return new SupportPhrase(words);
    }

    public String toString(SupportPhrase supportPhrase) {
        return supportPhrase.content();
    }

}

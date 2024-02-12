package ru.t1academy.controller;

import ru.t1academy.context.annotation.factory.Autowired;
import ru.t1academy.context.annotation.stereotype.Controller;
import ru.t1academy.service.PsySupportService;

@Controller
public class PsySupportControllerImpl implements PsySupportController {
    @Autowired
    private PsySupportService supportService;

    @Override
    public void addSupportPhrase(String words) {
        supportService.addSupportWords(words);
    }

    @Override
    public String getRandomSupportPhrase() {
        return supportService.getRandomSupportResponse();
    }
}

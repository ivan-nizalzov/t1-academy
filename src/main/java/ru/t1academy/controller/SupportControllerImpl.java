package ru.t1academy.controller;

import ru.t1academy.context.annotation.stereotype.Controller;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.service.SupportService;

import java.io.IOException;

@Controller
public class SupportControllerImpl implements SupportController {
    public SupportService supportService;

    public SupportControllerImpl(SupportService supportService) {
        this.supportService = supportService;
    }

    @Override
    public void addSupportPhrase(SupportPhrase supportPhrase) throws IOException {
        supportService.addSupportPhrase(supportPhrase);
    }

    @Override
    public SupportPhrase getRandomSupportPhrase() {
        return supportService.getRandomSupportPhrase();
    }

}

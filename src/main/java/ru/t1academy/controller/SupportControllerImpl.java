package ru.t1academy.controller;

import ru.t1academy.context.annotation.factory.Autowired;
import ru.t1academy.context.annotation.mapping.GetMapping;
import ru.t1academy.context.annotation.mapping.PostMapping;
import ru.t1academy.context.annotation.stereotype.Controller;
import ru.t1academy.service.SupportService;

@Controller
public class SupportControllerImpl implements SupportController {
    @Autowired
    private SupportService supportService;

    public SupportControllerImpl() {

    }

    public SupportControllerImpl(SupportService supportService) {
        this.supportService = supportService;
    }

    @Override
    @PostMapping("/v1/support")
    public void addSupportPhrase(String words) {
        supportService.addSupportWords(words);
    }

    @Override
    @GetMapping("/v1/support")
    public String getRandomSupportPhrase() {
        return supportService.getRandomSupportResponse();
    }
}

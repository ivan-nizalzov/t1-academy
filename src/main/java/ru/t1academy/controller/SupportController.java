package ru.t1academy.controller;

import ru.t1academy.context.annotation.mapping.GetMapping;
import ru.t1academy.context.annotation.mapping.PostMapping;
import ru.t1academy.context.annotation.stereotype.Controller;
import ru.t1academy.model.SupportPhrase;

import java.io.IOException;

@Controller
public interface SupportController {
    @PostMapping("/v1/support")
    void addSupportPhrase(SupportPhrase supportPhrase) throws IOException;

    @GetMapping("/v1/support")
    SupportPhrase getRandomSupportPhrase();

}

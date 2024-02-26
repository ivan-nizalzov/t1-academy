package ru.t1academy.helpservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.t1academy.helpservice.model.SupportPhrase;
import ru.t1academy.helpservice.service.SupportService;

@RestController
@RequestMapping(path = "/v1/support")
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;

    @PostMapping()
    public void addSupportPhrase(SupportPhrase supportPhrase) {
        supportService.addSupportPhrase(supportPhrase);
    }

    @GetMapping()
    public SupportPhrase getRandomSupportPhrase() {
        return supportService.getRandomSupportPhrase();
    }

}

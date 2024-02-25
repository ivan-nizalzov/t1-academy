package ru.t1academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.t1academy.messageBroker.publisher.MessagePublisher;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.service.SupportService;

@RestController
@RequestMapping(path = "/v1/support")
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;
    private final MessagePublisher<SupportPhrase> messagePublisher;

    @PostMapping()
    public void addSupportPhrase(SupportPhrase supportPhrase) {
        messagePublisher.publish(supportPhrase);
        new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public SupportPhrase getRandomSupportPhrase() {
        return supportService.getRandomSupportPhrase();
    }

}

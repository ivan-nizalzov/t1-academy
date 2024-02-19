package ru.t1academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1academy.model.SupportPhrase;
import ru.t1academy.service.SupportService;

import java.io.IOException;

@RestController
@RequestMapping("/v1/support")
@RequiredArgsConstructor
public class SupportController {
    private final SupportService supportService;

    @PostMapping
    public ResponseEntity<Void> addSupportPhrase(@RequestBody SupportPhrase supportPhrase) throws IOException {
        supportService.addSupportPhrase(supportPhrase);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SupportPhrase> getRandomSupportPhrase() {
        return ResponseEntity.ok(supportService.getRandomSupportPhrase());
    }

}

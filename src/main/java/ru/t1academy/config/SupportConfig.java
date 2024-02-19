package ru.t1academy.config;

import ru.t1academy.controller.SupportController;
import ru.t1academy.repository.SupportRepository;
import ru.t1academy.repository.SupportRepositoryImpl;
import ru.t1academy.service.SupportService;
import ru.t1academy.service.SupportServiceImpl;

public class SupportConfig {
    public SupportRepository supportRepository() {
        return new SupportRepositoryImpl();
    }

    public SupportService supportService(SupportRepository supportRepository) {
        return new SupportServiceImpl(supportRepository);
    }

    public SupportController supportController(SupportService supportService) {
        return new SupportController(supportService);
    }

}

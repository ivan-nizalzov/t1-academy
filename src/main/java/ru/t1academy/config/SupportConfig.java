package ru.t1academy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.t1academy.controller.SupportController;
import ru.t1academy.repository.SupportRepository;
import ru.t1academy.repository.SupportRepositoryImpl;
import ru.t1academy.service.SupportService;
import ru.t1academy.service.SupportServiceImpl;

@Configuration
public class SupportConfig {
    @Bean
    public SupportRepository supportRepository() {
        return new SupportRepositoryImpl();
    }

    @Bean
    public SupportService supportService(SupportRepository supportRepository) {
        return new SupportServiceImpl(supportRepository);
    }

    @Bean
    public SupportController supportController(SupportService supportService) {
        return new SupportController(supportService);
    }

}

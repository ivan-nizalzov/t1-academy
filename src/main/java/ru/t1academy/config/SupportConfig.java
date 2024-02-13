package ru.t1academy.config;

import ru.t1academy.context.ApplicationContext;
import ru.t1academy.context.ApplicationContextImpl;
import ru.t1academy.context.annotation.stereotype.Bean;
import ru.t1academy.context.annotation.stereotype.Configuration;
import ru.t1academy.controller.SupportController;
import ru.t1academy.controller.SupportControllerImpl;
import ru.t1academy.repository.SupportRepository;
import ru.t1academy.repository.SupportRepositoryImpl;
import ru.t1academy.service.SupportService;
import ru.t1academy.service.SupportServiceImpl;

@Configuration
public class SupportConfig {
    /*private final String SUPPORT_REPOSITORY_NAME = "supportRepository";
    private final String SUPPORT_SERVICE_NAME = "supportService";
    private final String SUPPORT_CONTROLLER_NAME = "supportController";

    ApplicationContext context = new ApplicationContextImpl();
    SupportRepository supportRepository = new SupportRepositoryImpl();
    SupportService supportService = new SupportServiceImpl();
    SupportController supportController = new SupportControllerImpl();

    public void registerContext() {
        context.registerBean(SUPPORT_REPOSITORY_NAME, supportRepository);
        context.registerBean(SUPPORT_SERVICE_NAME, supportService);
        context.registerBean(SUPPORT_CONTROLLER_NAME, supportController);

        context.autowireBeans();
    }

    public Object getInstance() {
        return context.getBean(SUPPORT_SERVICE_NAME);
    }

    public <T> T getInstance(Class<T> beanClass) {
        return context.getBean(beanClass);
    }*/

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
        return new SupportControllerImpl(supportService);
    }

}

package ru.t1academy.config;

import ru.t1academy.context.ApplicationContext;
import ru.t1academy.context.PsyApplicationContext;
import ru.t1academy.context.annotation.stereotype.Configuration;
import ru.t1academy.repository.PsySupportRepository;
import ru.t1academy.repository.PsySupportRepositoryImpl;
import ru.t1academy.service.PsySupportService;
import ru.t1academy.service.PsySupportServiceImpl;

@Configuration
public class PsySupportConfig {
    private final String SUPPORT_REPOSITORY_NAME = "supportRepository";
    private final String SUPPORT_SERVICE_NAME = "supportService";
    ApplicationContext context = new PsyApplicationContext();
    PsySupportRepository supportRepository = new PsySupportRepositoryImpl();
    PsySupportService supportService = new PsySupportServiceImpl();
    //PsySupportController supportController = new PsySupportControllerImpl();

    public void registerContext() {
        context.registerBean(SUPPORT_REPOSITORY_NAME, supportRepository);
        context.registerBean(SUPPORT_SERVICE_NAME, supportService);
        //context.registerBean("supportController", supportController);

        context.autowireBeans();
    }

    public Object getInstance() {
        PsySupportService supportServiceInstance = (PsySupportServiceImpl) context.getBean(SUPPORT_SERVICE_NAME);
        return supportServiceInstance;
    }

}

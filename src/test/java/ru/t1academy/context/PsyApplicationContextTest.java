package ru.t1academy.context;

import org.junit.jupiter.api.Test;
import ru.t1academy.controller.SupportController;
import ru.t1academy.service.SupportService;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class PsyApplicationContextTest {
    @Test
    public void shouldGetServiceBeanFromApplicationContext() {
        ApplicationContext applicationContext;
        try {
            applicationContext = new ApplicationContextImpl();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        SupportService supportService = applicationContext.getBean(SupportService.class);
        assertNotNull(supportService);
    }

    @Test
    public void shouldGetControllerBeanFromApplicationContext() {
        ApplicationContext applicationContext;
        try {
            applicationContext = new ApplicationContextImpl();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(applicationContext.getBean(SupportController.class));
    }

}
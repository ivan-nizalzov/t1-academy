package ru.t1academy.dispatcher.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PsyHandlerMethod {
    private final Object controller;
    private final Method method;

    public PsyHandlerMethod(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object invoke(Object... args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(controller, args);
    }

    public Class<?> getControllerClass() {
        return controller.getClass();
    }
}

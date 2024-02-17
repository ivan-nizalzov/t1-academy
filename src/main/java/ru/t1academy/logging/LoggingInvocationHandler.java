package ru.t1academy.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.t1academy.context.annotation.factory.Logged;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Logger logger;

    public LoggingInvocationHandler(Object target) {
        this.target = target;
        logger = LoggerFactory.getLogger(target.getClass());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Logged.class)) {
            logger.info("Start invoking method: [%s]".formatted(method.getName()));
            Object invoke = method.invoke(target, args);
            logger.info("Method [%s] invoked with result: [%s]".formatted(method.getName(), invoke));
            return invoke;
        }

        return method.invoke(target, args);
    }

}

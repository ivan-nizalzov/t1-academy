package ru.t1academy.context;

import org.reflections.Reflections;
import ru.t1academy.context.annotation.stereotype.Bean;
import ru.t1academy.context.annotation.stereotype.Configuration;
import ru.t1academy.logging.LoggingInvocationHandler;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContextImpl implements ApplicationContext {
    private static final Map<Class<?>, Object> BEAN_MAP = new ConcurrentHashMap<>();

    public ApplicationContextImpl() throws InvocationTargetException, IllegalAccessException {
        final List<?> configurationList = getConfiguration();
        for (Object config : configurationList) {

            for (Method method : Arrays.stream(config.getClass().getMethods()).filter(method ->
                    method.isAnnotationPresent(Bean.class) && method.getParameters().length == 0).toList()) {
                BEAN_MAP.put(method.getReturnType(), wrapWithLogging(method.invoke(config)));
            }

            for (Method method : Arrays.stream(config.getClass().getMethods()).filter(method ->
                    method.isAnnotationPresent(Bean.class) && method.getParameters().length != 0).toList()) {

                final List<Object> parameterList = new ArrayList<>();

                for (Parameter parameter : method.getParameters()) {
                    parameterList.add(BEAN_MAP.get(parameter.getType()));
                }
                BEAN_MAP.put(method.getReturnType(), wrapWithLogging(method.invoke(config, parameterList.toArray())));
            }

        }
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return (T) Optional.ofNullable(BEAN_MAP.get(beanClass))
                .orElseThrow(() -> new RuntimeException("No instance with type: %s".formatted(beanClass)));
    }

    private List<?> getConfiguration() {
        Reflections reflections = new Reflections("ru.t1academy.config");
        return reflections.getTypesAnnotatedWith(Configuration.class)
                .stream()
                .map(type -> {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

   private Object wrapWithLogging(Object object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new LoggingInvocationHandler(object)
        );
    }

}

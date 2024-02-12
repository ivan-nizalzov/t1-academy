package ru.t1academy.dispatcher.handler;

import ru.t1academy.dispatcher.HttpMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PsyHandlerMapping {
    private final Map<String, Map<HttpMethod, PsyHandlerMethod>> handlerMethods;

    public PsyHandlerMapping(Map<Class<?>, Object> beans) {
        handlerMethods = new HashMap<>();

        // Поиск и регистрация методов контроллеров с помощью аннотаций
        for (Object bean : beans.values()) {
            Class<?> controllerClass = bean.getClass();
            RequestMapping requestMapping = controllerClass.getAnnotation(RequestMapping.class);

            if (requestMapping != null) {
                String baseUrl = requestMapping.value();
                Method[] methods = controllerClass.getMethods();

                for (Method method : methods) {
                    PostMapping postMapping = method.getAnnotation(PostMapping.class);
                    GetMapping getMapping = method.getAnnotation(GetMapping.class);

                    if (postMapping != null) {
                        String url = baseUrl + postMapping.value();
                        PsyHandlerMethod handlerMethod = new PsyHandlerMethod(bean, method);
                        addHandlerMethod(url, HttpMethod.POST, handlerMethod);
                    } else if (getMapping != null) {
                        String url = baseUrl + getMapping.value();
                        PsyHandlerMethod handlerMethod = new PsyHandlerMethod(bean, method);
                        addHandlerMethod(url, HttpMethod.GET, handlerMethod);
                    }
                }
            }
        }
    }

    private void addHandlerMethod(String url, HttpMethod httpMethod, PsyHandlerMethod handlerMethod) {
        if (!handlerMethods.containsKey(url)) {
            handlerMethods.put(url, new HashMap<>());
        }

        Map<HttpMethod, PsyHandlerMethod> methodsMap = handlerMethods.get(url);
        methodsMap.put(httpMethod, handlerMethod);
    }

    public PsyHandlerMethod getHandlerMethod(String url, HttpMethod httpMethod) {
        Map<HttpMethod, PsyHandlerMethod> methodsMap = handlerMethods.get(url);
        if (methodsMap != null) {
            return methodsMap.get(httpMethod);
        }
        return null;
    }
}

package ru.t1academy.dispatcher;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

import ru.t1academy.context.ApplicationContext;
import ru.t1academy.context.ApplicationContextImpl;
import ru.t1academy.context.annotation.stereotype.Controller;
import ru.t1academy.dispatcher.handler.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PsyDispatcherServlet extends HttpServlet {
    private final ApplicationContext applicationContext = new ApplicationContextImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<Class<?>, Object> controllerMap = new ConcurrentHashMap<>();
    private final RequestHandler getRequestHandler = new GetRequestHandler();
    private final RequestHandler postRequestHandler = new PostRequestHandler();

    public PsyDispatcherServlet() throws InvocationTargetException, IllegalAccessException {

    }

    @Override
    public void init() {
        Reflections reflections = new Reflections("ru.t1academy");
        reflections.getTypesAnnotatedWith(Controller.class).forEach(type -> {
            if (type.isInterface()) {
                controllerMap.put(type, applicationContext.getBean(type));
                String test = "test";
            }
        });
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response = getRequestHandler.handleRequest(controllerMap, request, response, objectMapper);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response = postRequestHandler.handleRequest(controllerMap, request, response, objectMapper);
    }

}

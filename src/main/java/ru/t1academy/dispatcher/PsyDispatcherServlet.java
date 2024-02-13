package ru.t1academy.dispatcher;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;
import ru.t1academy.config.SupportConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.t1academy.context.annotation.stereotype.Controller;
import ru.t1academy.controller.SupportController;
import ru.t1academy.controller.SupportControllerImpl;
import ru.t1academy.dispatcher.handler.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class PsyDispatcherServlet extends HttpServlet {
    private final static Logger log = LoggerFactory.getLogger(PsyDispatcherServlet.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<Class<?>, Object> controllerMap = new ConcurrentHashMap<>();
    private final RequestHandler getRequestHandler = new GetRequestHandler();
    private final RequestHandler postRequestHandler = new PostRequestHandler();
    private final SupportConfig supportConfig;
    private final SupportController supportController;

    public PsyDispatcherServlet() {
        supportConfig = new SupportConfig();
        supportConfig.registerContext();
        supportController = (SupportControllerImpl) supportConfig.getInstance();
    }

    @Override
    public void init() {
        Reflections reflections = new Reflections("ru.t1academy");
        reflections.getTypesAnnotatedWith(Controller.class).forEach(type ->
                controllerMap.put(type, supportConfig.getInstance(type)));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        getRequestHandler.handleRequest(controllerMap, request, response, objectMapper);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        postRequestHandler.handleRequest(controllerMap, request, response, objectMapper);
    }

}

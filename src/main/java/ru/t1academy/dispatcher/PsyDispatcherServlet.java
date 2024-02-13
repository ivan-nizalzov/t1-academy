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

        /*controllerMap.forEach((type, object) -> {
            Optional<Method> first = Arrays.stream(type.getMethods())
                    .filter(method -> method.isAnnotationPresent(GetMapping.class) &&
                            method.getAnnotation(GetMapping.class).value().equals(request.getRequestURI())
                    )
                    .findFirst();

            if (first.isPresent()) {
                try {
                    Object invoke = first.get().invoke(object);
                    response.setStatus(200);
                    response.setContentType("application/json");
                    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    response.getWriter().print(objectMapper.writeValueAsString(invoke));
                } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                    response.setStatus(500);
                }
            } else {
                response.setStatus(404);
            }
        });*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response = postRequestHandler.handleRequest(controllerMap, request, response, objectMapper);

        /*controllerMap.forEach((type, object) -> {
            Optional<Method> first = Arrays.stream(type.getMethods())
                    .filter(method -> method.isAnnotationPresent(PostMapping.class) &&
                            method.getAnnotation(PostMapping.class).value().equals(request.getRequestURI()))
                    .findFirst();

            if (first.isPresent()) {
                try {
                    StringBuilder sb = new StringBuilder();
                    request.getReader().lines().forEach(sb::append);
                    Optional<Class<?>> parameter = Arrays.stream(first.get().getParameterTypes()).findFirst();
                    Object result;

                    if (parameter.isPresent()) {
                        result = first.get().invoke(object, objectMapper.readValue(sb.toString(), parameter.get()));
                    } else {
                        result = first.get().invoke(object);
                    }

                    response.setContentType("application/json");
                    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                    if (result != null) {
                        response.setStatus(200);
                        response.getWriter().print(objectMapper.writeValueAsString(result));
                    } else {
                        response.setStatus(204);
                    }
                } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                    response.setStatus(500);
                }
            } else {
                response.setStatus(404);
            }
        });*/

    }

}

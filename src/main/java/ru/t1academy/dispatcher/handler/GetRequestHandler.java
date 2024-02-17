package ru.t1academy.dispatcher.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.t1academy.context.annotation.mapping.GetMapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class GetRequestHandler implements RequestHandler {

    @Override
    public HttpServletResponse handleRequest(Map<Class<?>, Object> controllerMap,
                                             HttpServletRequest request,
                                             HttpServletResponse response,
                                             ObjectMapper objectMapper) {

        controllerMap.forEach((type, object) -> {
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
        });

        return response;
    }

}

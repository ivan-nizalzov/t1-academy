package ru.t1academy.dispatcher.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.t1academy.context.annotation.mapping.PostMapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class PostRequestHandler implements RequestHandler {

    @Override
    public HttpServletResponse handleRequest(Map<Class<?>, Object> controllerMap,
                                             HttpServletRequest request,
                                             HttpServletResponse response,
                                             ObjectMapper objectMapper) {

        controllerMap.forEach((type, object) -> {
            Optional<Method> first = Arrays.stream(type.getMethods())
                    .filter(method -> method.isAnnotationPresent(PostMapping.class) &&
                            method.getAnnotation(PostMapping.class).value().equals(request.getRequestURI()))
                    .findFirst();

            if (first.isPresent()) {
                try {
                    StringBuilder sb = new StringBuilder();
                    request.getReader().lines().forEach(sb::append);
                    Optional<Class<?>> param = Arrays.stream(first.get().getParameterTypes()).findFirst();
                    Object result;

                    if (param.isPresent()) {
                        result = first.get().invoke(object, objectMapper.readValue(sb.toString(), param.get()));
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
        });

        return response;
    }

}

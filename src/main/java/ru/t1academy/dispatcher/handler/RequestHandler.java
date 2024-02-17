package ru.t1academy.dispatcher.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface RequestHandler {
    HttpServletResponse handleRequest(Map<Class<?>, Object> controllerMap,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      ObjectMapper objectMapper);
}

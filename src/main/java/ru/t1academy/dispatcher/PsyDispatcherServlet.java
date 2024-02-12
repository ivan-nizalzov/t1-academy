package ru.t1academy.dispatcher;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.t1academy.config.PsySupportConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.t1academy.context.annotation.stereotype.Controller;
import ru.t1academy.service.PsySupportService;
import ru.t1academy.service.PsySupportServiceImpl;

@Controller
public class PsyDispatcherServlet extends HttpServlet {
    private final static Logger log = LoggerFactory.getLogger(PsyDispatcherServlet.class);
    private final PsySupportConfig supportConfig;
    private final PsySupportService supportService;

    public PsyDispatcherServlet() {
        supportConfig = new PsySupportConfig();
        supportConfig.registerContext();
        supportService = (PsySupportServiceImpl) supportConfig.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        /*String url = request.getRequestURI();
        HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod());

        PsyHandlerMapping handlerMapping = applicationContext.getHandlerMapping();
        PsyHandlerExecutor handlerExecutor = applicationContext.getHandlerExecutor();

        PsyHandlerMethod handlerMethod = handlerMapping.getHandlerMethod(url, httpMethod);
        if (handlerMethod != null) {
            Object controller = applicationContext.getBean(handlerMethod.getControllerClass());
            Object result = handlerExecutor.execute(handlerMethod, controller, request, response);
            // Преобразование результата в JSON и отправка клиенту
        } else {
            // Обработка несуществующих URL
        }
    */
    }

}

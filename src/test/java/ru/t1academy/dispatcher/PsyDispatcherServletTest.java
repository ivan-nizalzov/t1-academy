package ru.t1academy.dispatcher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PsyDispatcherServletTest {
    private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    private static final StringWriter writer = new StringWriter();

    @BeforeAll
    public static void setup() throws IOException {
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
    }

    @Test
    public void shouldReturnSuccessForPostRequest() {
        PsyDispatcherServlet dispatcherServlet;
        try {
            dispatcherServlet = new PsyDispatcherServlet();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        dispatcherServlet.init();

        String testPhrase = "{\"content\":\"A random support phrase\"}";

        try {
            when(request.getReader()).thenReturn(new BufferedReader(new StringReader(testPhrase)));
            when(request.getRequestURI()).thenReturn("/v1/support");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dispatcherServlet.doPost(request, response);
        verify(response).setStatus(204);
    }

}
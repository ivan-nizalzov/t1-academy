package ru.t1academy.dispatcher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
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
    public void shouldDoSuccessMappingOfGetRequest() throws IOException {
        String expectedResult = "{\"phraseText\":\"У тебя все получится!\"}";
        PsyDispatcherServlet dispatcherServlet = new PsyDispatcherServlet();
        dispatcherServlet.init();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        when(request.getRequestURI()).thenReturn("/v1/support");
        dispatcherServlet.doGet(request, response);
        assertEquals(expectedResult, writer.toString());
        verify(response).setStatus(200);
    }

    @Test
    public void shouldDoSuccessMappingOfPostRequest() throws IOException {
        PsyDispatcherServlet dispatcherServlet = new PsyDispatcherServlet();
        dispatcherServlet.init();
        String newPhrase = """
                {
                    "phraseText": "У тебя все получится!"
                }
                """;
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(newPhrase)));
        when(request.getRequestURI()).thenReturn("/v1/support");
        dispatcherServlet.doPost(request, response);
        verify(response).setStatus(204);
    }

    @Test
    public void shouldNotFoundMappingOfGetRequest() throws IOException {
        PsyDispatcherServlet dispatcherServlet = new PsyDispatcherServlet();
        dispatcherServlet.init();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        when(request.getRequestURI()).thenReturn("/unsupported");
        dispatcherServlet.doGet(request, response);
        verify(response).setStatus(404);
    }

    @Test
    public void shouldNotFoundMappingOfPostRequest() throws IOException {
        PsyDispatcherServlet dispatcherServlet = new PsyDispatcherServlet();
        dispatcherServlet.init();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));
        when(request.getRequestURI()).thenReturn("/unsupported");
        dispatcherServlet.doPost(request, response);
        verify(response).setStatus(404);
    }

}
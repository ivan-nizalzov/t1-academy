package ru.t1_academy.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.t1_academy.service.PsychologySupportService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

import static org.mockito.Mockito.*;

public class PsySupportHttpServletTest {
    @Mock
    private PsychologySupportService supportService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;

    private PsySupportHttpServlet servlet;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        servlet = new PsySupportHttpServlet(supportService);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testDoGet() throws IOException {
        when(supportService.getRandomSupportResponse()).thenReturn("Test Response");

        servlet.doGet(request, response);

        verify(response).setContentType("text/plain");
        verify(writer).println("Test Response");
    }

    @Test
    public void testDoPost() throws IOException {
        when(supportService.addSupportWords(anyString())).thenReturn("Test Result");

        String requestBody = "Test Request Body";
        BufferedReader reader = new BufferedReader(new StringReader(requestBody));
        when(request.getReader()).thenReturn(reader);

        servlet.doPost(request, response);

        verify(response).setContentType("text/plain");
        verify(response).setCharacterEncoding("UTF-8");
        verify(writer).println("Test Result");
    }
}

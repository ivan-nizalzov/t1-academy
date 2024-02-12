package ru.t1academy.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.t1academy.service.PsySupportService;
import ru.t1academy.service.PsySupportServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/v1/support")
public class PsySupportHttpServlet extends HttpServlet {
    private final PsySupportService supportService;

    public PsySupportHttpServlet(PsySupportService supportService) {
        this.supportService = supportService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String randomResponse = supportService.getRandomSupportResponse();
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        writer.println(randomResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newSupportPhrase = sb.toString();
        String result = supportService.addSupportWords(newSupportPhrase);
        writer.println(result);
    }

}

package org.maxim.RestApi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.maxim.RestApi.model.Event;
import org.maxim.RestApi.service.impl.EventServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/events")
public class EventRestControllerV1 extends HttpServlet {
    private final String ENCODING = "UTF-8";
    private final String CONTENT_TYPE = "application/json; charset=" + ENCODING;

    private EventServiceImpl eventService = new EventServiceImpl();

    private Integer extractIdFromPath(String pathInfo) {
        try {
            String[] pathParams = pathInfo.split("/");
            return Integer.valueOf(pathParams[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
        List<Event> responseData = new ArrayList<>();
        String pathInfo = req.getPathInfo();

        if (pathInfo == null) {
            List<Event> events = eventService.getAll();
            if (events != null || !events.isEmpty()) {
                responseData.addAll(events);
            }
            resp.setStatus(200);
        } else {
            Integer id = extractIdFromPath(pathInfo);
            Event event = eventService.getById(id);
            if (event != null) {
                responseData.add(event);
                resp.setStatus(200);
            } else {
                responseData = null;
                resp.setStatus(400);
            }
        }
        String jsonResponse = new ObjectMapper().writeValueAsString(responseData);
        PrintWriter message = resp.getWriter();
        message.write(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
        resp.setStatus(405);
        PrintWriter message = resp.getWriter();
        message.write("Method error in events");
    }

    @Override
    protected void doPut(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
        Boolean responseData = false;
        String pathInfo = req.getPathInfo();

        if (pathInfo == null ) {
            responseData = null;
            resp.setStatus(400);
        } else {
            Integer id = extractIdFromPath(pathInfo);
            if (eventService.deleteById(id)) {
                responseData = true;
                resp.setStatus(200);
            } else {
                responseData = false;
                resp.setStatus(200);
            }
        }
        String jsonResponse = new ObjectMapper().writeValueAsString(responseData);
        PrintWriter message = resp.getWriter();
        message.write(jsonResponse);
    }
}

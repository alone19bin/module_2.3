package org.maxim.RestApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.maxim.RestApi.model.User;
import org.maxim.RestApi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/")
public class UserRestControllerV1 extends HttpServlet {

    private final String ENCODING = "UTF-8";
    private final String CONTENT_TYPE = "application/json; charset=" + ENCODING;

    private UserServiceImpl userService = new UserServiceImpl();

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
        List<User> responseData = new ArrayList<>();
        String pathInfo = req.getPathInfo();

        if (pathInfo == null ) {

            List<User> users = userService.getAll();
            if (users != null && !users.isEmpty()) {
                responseData.addAll(users);
            }
            resp.setStatus(200);
        } else {
            Integer id = extractIdFromPath(pathInfo);
            User user = userService.getById(id);
            if (user != null) {
                responseData.add(user);
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
        List<User> responseData = new ArrayList<>();
        String pathInfo = req.getPathInfo();

        if (pathInfo == null) {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            if (user != null) {
                user.setId(null);
                User savedUser = userService.save(user);
                if (savedUser != null) {
                    responseData.add(savedUser);
                    resp.setStatus(201);
                } else {
                    responseData = null;
                    resp.setStatus(503);
                }
            }
        } else {
            responseData = null;
            resp.setStatus(400);
        }
        String jsonResponse = new ObjectMapper().writeValueAsString(responseData);
        PrintWriter message = resp.getWriter();
        message.write(jsonResponse);
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
            if (userService.deleteById(id)) {
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
        List<User> responseData = new ArrayList<>();
        String pathInfo = req.getPathInfo();

        if (pathInfo == null ) {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            User updatedUser = userService.update(user);
            if (updatedUser != null) {
                responseData.add(updatedUser);
                resp.setStatus(200);
            } else {
                responseData = null;
                resp.setStatus(400);
            }
        } else {
            responseData = null;
            resp.setStatus(400);
        }
        String jsonResponse = new ObjectMapper().writeValueAsString(responseData);
        PrintWriter message = resp.getWriter();
        message.write(jsonResponse);
    }
}

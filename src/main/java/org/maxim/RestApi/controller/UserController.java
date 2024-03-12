package org.maxim.RestApi.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maxim.RestApi.model.User;
import org.maxim.RestApi.repository.hiber.UserHib;
import org.maxim.RestApi.service.UserService;
import com.google.gson.Gson;
import org.maxim.RestApi.utils.ServletUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@WebServlet("/api/users/*")
public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> responseData = new ArrayList<>();
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (pathInfo == null){
            List<User> users = userService.getAll();
            if (users != null && !users.isEmpty()) {
                responseData.addAll(users);
            }
            resp.setStatus(200);
        } else {
            Integer id = ServletUtil.Path(pathInfo);
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> responseData = new ArrayList<>();
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (pathInfo == null) {
            List<User> users = userService.getAll();
            if (users != null && !users.isEmpty()) {
                responseData.addAll(users);
            }
            resp.setStatus(200);
        } else {
            Integer id = ServletUtil.Path(pathInfo);
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
        List<User> responseData = new ArrayList<>();
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (pathInfo == null) {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            if (user != null) {
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
        Boolean responseData = false;
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (pathInfo == null || "/".equals(pathInfo)) {
            responseData = null;
            resp.setStatus(400);
        } else {
            Integer id = ServletUtil.Path(pathInfo);
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

}

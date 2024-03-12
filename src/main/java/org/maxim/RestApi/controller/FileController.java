package org.maxim.RestApi.controller;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maxim.RestApi.model.File;
import org.maxim.RestApi.repository.FileRepository;
import org.maxim.RestApi.repository.hiber.EventHib;
import org.maxim.RestApi.repository.hiber.FileHib;
import org.maxim.RestApi.repository.hiber.UserHib;
import org.maxim.RestApi.service.EventService;
import org.maxim.RestApi.service.FileService;
import org.maxim.RestApi.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/files/")
public class FileController extends HttpServlet {
    private  final FileRepository fileRepository = new FileHib();
    private final EventService eventService = new EventService();
    private final UserService userService = new UserService();
    private final FileService fileService = new FileService();
    private final Gson GSON = new Gson();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = GSON.fromJson(req.getReader(), File.class);
        Integer id = file.getId();
        if (id == 0) {
            List<File> fileList = fileService.getAll();
            resp.setContentType("application/json");
            PrintWriter printWriter = resp.getWriter();
            printWriter.print(fileList);
        } else {
            File fileId = fileService.getById(id);
            resp.setContentType("application/json");
            PrintWriter printWriter = resp.getWriter();
            printWriter.print(fileId);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = GSON.fromJson(req.getReader(), File.class);
        fileService.update(file);
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("Update file");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = GSON.fromJson(req.getReader(), File.class);
        fileService.deleteById(file.getId());
        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("Delete file");
    }
}
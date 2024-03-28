package org.maxim.RestApi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.maxim.RestApi.model.Event;
import org.maxim.RestApi.model.File;
import org.maxim.RestApi.model.User;
import org.maxim.RestApi.service.impl.EventServiceImpl;
import org.maxim.RestApi.service.impl.FileServiceImpl;
import org.maxim.RestApi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@WebServlet("/files")
public class FileRestControllerV1 extends HttpServlet {
    public class FileRestController extends HttpServlet {
        private final String ENCODING = "UTF-8";
        private final String CONTENT_TYPE = "storage.json; charset=" + ENCODING;
        private final String FILE_STORAGE_PATH = "./src/main/resources/";
        private final int MEM_MAX_SIZE = 10_000 * 1024;
        private final int FILE_MAX_SIZE = 1_000 * 1024;

        private final FileServiceImpl fileService = new FileServiceImpl();
        private final UserServiceImpl userService = new UserServiceImpl();
        private final EventServiceImpl eventService = new EventServiceImpl();

        private Integer FromPath(String pathInfo) {
            try {
                String[] pathParams = pathInfo.split("/");
                return Integer.valueOf(pathParams[1]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private String approveFileName(String path, String fileName) {
            while (Files.exists(Path.of(path, fileName))) fileName = "Copy " + fileName;
            return fileName;
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setCharacterEncoding(ENCODING);
            resp.setContentType(CONTENT_TYPE);
            List<File> responseData = new ArrayList<>();
            String pathInfo = req.getPathInfo();

            if (pathInfo == null) {
                List<File> files = fileService.getAll();
                if (files != null && !files.isEmpty()) {
                    responseData.addAll(files);
                }
                resp.setStatus(200);
            } else {
                Integer id = FromPath(pathInfo);
                File file = fileService.getById(id);
                if (file != null) {
                    responseData.add(file);
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
            List<File> responseData = new ArrayList<>();
            String pathInfo = req.getPathInfo();

            if (pathInfo == null ) {
                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
                diskFileItemFactory.setRepository(new java.io.File(FILE_STORAGE_PATH));
                diskFileItemFactory.setSizeThreshold(MEM_MAX_SIZE);

                ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
                upload.setSizeMax(FILE_MAX_SIZE);

                try {
                    List<FileItem> fileItems = upload.parseRequest(req);
                    Iterator<FileItem> iterator = fileItems.iterator();
                    while (iterator.hasNext()) {
                        FileItem fileItem = iterator.next();
                        String fileName = fileItem.getName();
                        fileName = approveFileName(FILE_STORAGE_PATH, fileName);
                        java.io.File fileOnDisk = new java.io.File(FILE_STORAGE_PATH + fileName);
                        fileItem.write(fileOnDisk);

                        File fileToSave = new File(fileName, FILE_STORAGE_PATH);
                        File fileSaved = fileService.save(fileToSave);
                        if (fileSaved != null) {
                            Integer userId = Integer.valueOf(req.getHeader("User-Id"));
                            User currentUser = userService.getById(userId);
                            Event currentEvent = new Event(currentUser, fileSaved);
                            List<Event> eventsForUser = currentUser.getEvents();
                            eventsForUser.add(currentEvent);
                            currentUser.setEvents(eventsForUser);
                            eventService.save(currentEvent);
                            responseData.add(fileSaved);
                            resp.setStatus(201);
                        } else {
                            if (fileOnDisk.exists()) {
                                fileOnDisk.delete();
                            }
                            fileItem.delete();
                            responseData = null;
                        }
                    }
                } catch (Exception e) {
                    responseData = null;
                    resp.setStatus(400);
                    e.printStackTrace();
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
                Integer id = FromPath(pathInfo);
                if (fileService.deleteById(id)) {
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
            List<File> responseData = new ArrayList<>();
            String pathInfo = req.getPathInfo();

            if (pathInfo == null ) {
                File file = new ObjectMapper().readValue(req.getInputStream(), File.class);
                File updatedFile = fileService.update(file);
                if (updatedFile != null) {
                    responseData.add(updatedFile);
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
}
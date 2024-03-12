package org.maxim.RestApi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.OptionalLong;

public class ServletUtil {
    public static Integer Path(String pathInfo) {
        try {
            String[] pathParams = pathInfo.split("/");
            Integer.valueOf(pathParams[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void writeJson(HttpServletResponse resp, Object object) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(object);
        resp.getWriter().write(json);
    }
}

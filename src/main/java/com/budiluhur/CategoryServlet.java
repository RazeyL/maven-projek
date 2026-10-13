package com.budiluhur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    private final List<Category> categories = Arrays.asList(
            new Category("CAT-01", "Elektronik"),
            new Category("CAT-02", "Aksesori"),
            new Category("CAT-03", "Peralatan Komputer"));

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Daftar Kategori</title></head>");
        out.println("<body>");
        out.println("<h2>Daftar Kategori</h2>");
        out.println("<table border='1' cellpadding='8'>");
        out.println("<tr><th>ID Kategori</th><th>Nama Kategori</th></tr>");

        for (Category category : categories) {
            out.println("<tr><td>" + category.getId() + "</td><td>"
                    + category.getName() + "</td></tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
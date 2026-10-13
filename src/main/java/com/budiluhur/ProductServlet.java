package com.budiluhur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() {
        // Inisialisasi Repository dan data awal
        productRepository = new ProductRepository();

        productRepository.addProduct(new Product(
            "PRD-01", "Keyboard Mechanical", 450000.0));

        productRepository.addProduct(new Product(
            "PRD-02", "Mouse Wireless Silent", 175000.0));

        productRepository.addProduct(new Product(
            "PRD-03", "Monitor Gaming 24 Inch", 2100000.0));
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        // Ambil query parameter id
        String id = request.getParameter("id");

        // Jika id diberikan
        if (id != null && !id.isEmpty()) {

            try {
                // Cari produk berdasarkan ID
                Product product = productRepository.findById(id);

                // Tampilkan detail produk
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Detail Produk</title>");
                out.println("</head>");
                out.println("<body>");

                out.println("<h2>=== DETAIL PRODUK ===</h2>");

                out.println("<table border='1' cellpadding='8'>");

                out.println("<tr>");
                out.println("<th>ID Produk</th>");
                out.println("<td>" + product.getId() + "</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>Nama Produk</th>");
                out.println("<td>" + product.getName() + "</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>Harga Satuan</th>");
                out.println("<td>Rp" + product.getPrice() + "</td>");
                out.println("</tr>");

                out.println("</table>");

                out.println("</body>");
                out.println("</html>");

            } catch (ProductNotFoundException e) {

                // Jika produk tidak ditemukan
                response.setStatus(
                    HttpServletResponse.SC_NOT_FOUND
                );

                out.println("<html>");
                out.println("<head>");
                out.println("<title>404 - Produk Tidak Ditemukan</title>");
                out.println("</head>");
                out.println("<body>");

                out.println(
                    "<h2 style='color:red;'>"
                    + "Error 404 - Produk Tidak Ditemukan"
                    + "</h2>"
                );

                out.println(
                    "<p style='color:red;'>"
                    + "Produk dengan ID <b>" + id + "</b> "
                    + "tidak ditemukan."
                    + "</p>"
                );

                out.println("</body>");
                out.println("</html>");
            }

            return;
        }

        // Jika id tidak diberikan, tampilkan semua produk
        List<Product> products =
            productRepository.findAll();

        out.println("<html>");
        out.println("<head><title>Katalog Produk Web</title></head>");
        out.println("<body>");

        out.println(
            "<h2>=== DAFTAR KATALOG PRODUK (WEB) ===</h2>"
        );

        out.println(
            "<table border='1' cellpadding='8'>"
        );

        out.println(
            "<tr>"
            + "<th>ID Produk</th>"
            + "<th>Nama Produk</th>"
            + "<th>Harga Satuan</th>"
            + "</tr>"
        );

        for (Product p : products) {
            out.println("<tr>");

            out.println(
                "<td>" + p.getId() + "</td>"
            );

            out.println(
                "<td>" + p.getName() + "</td>"
            );

            out.println(
                "<td>Rp" + p.getPrice() + "</td>"
            );

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
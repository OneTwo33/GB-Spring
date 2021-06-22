package ru.onetwo33;

import ru.onetwo33.persist.Product;
import ru.onetwo33.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();

        if (req.getPathInfo() == null) {
            List<Product> products = productRepository.findAll();

            pw.println("<table border=\"1\">");
            pw.println("<tr>");
            pw.println("<th>ID</th>");
            pw.println("<th>Title</th>");
            pw.println("<th>Cost</th>");
            pw.println("</tr>");
            for (Product product : products) {
                pw.println("<tr>");
                pw.println("<td>" + product.getId() + "</td>");
                pw.println("<td><a href='" + req.getContextPath() + req.getServletPath() + "/" + product.getId() + "'>" + product.getName() + "</a></td>");
                pw.println("<td>" + product.getCost() + "</td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
        } else {
            Long id = Long.parseLong(req.getPathInfo().substring(1)); // Уберем дробь "/" перед id
            Product product = productRepository.findById(id);

            pw.println("<h1>Карточка товара: " + product.getName() + "</h1>");
            pw.println("<p>ID: " + product.getId() + "</p>");
            pw.println("<p>Цена: " + product.getCost() + "</p>");
        }
    }
}
